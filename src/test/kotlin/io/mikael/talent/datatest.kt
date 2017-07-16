package io.mikael.talent

import io.mikael.talent.model.Person
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import javax.transaction.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
open class DataTest {

    @Autowired
    lateinit var pr: PersonRepository

    @Test
    @Transactional
    fun readData() {
        val c1 = Constructor(Person::class.java)
        resourceAsStream("data.yml").use { inputStream ->
            Yaml(c1).loadAll(inputStream).map { it as Person }.forEach { item ->
                println(item)
                pr.save(item)
            }
        }
        println(pr.findAll())
    }

}
