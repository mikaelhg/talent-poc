package io.mikael.talent

import io.mikael.talent.model.Person
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.InputStream
import javax.transaction.Transactional

@RunWith(SpringRunner::class)
@DataJpaTest
class DataTest {

    @Autowired
    lateinit var pr: PersonRepository

    @Test
    @Transactional
    fun readData() {
        val c1 = Constructor(Person::class.java)
        resourceAsStream("data.yml").use { inputStream ->
            Yaml(c1).loadAll<Person>(inputStream).map(pr::save)
        }
        println(pr.findAll())
    }

}

inline fun <reified T> Yaml.loadAll(inputStream: InputStream): Iterable<T> {
    return this.loadAll(inputStream) as Iterable<T>
}
