package io.mikael.talent

import io.mikael.talent.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.env.Environment
import org.springframework.core.io.Resource
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.InputStream

//@Component
open class DemoDataPopulator : ApplicationRunner {

    @Value("classpath:/data.yml")
    private lateinit var dataFile: Resource

    @Autowired
    private lateinit var env: Environment

    @Autowired
    private lateinit var personRepository: PersonRepository

    override fun run(args: ApplicationArguments) {
        if (env.activeProfiles.contains("prod")) {
            return
        }
        val c1 = Constructor(Person::class.java)
        dataFile.inputStream.use { inputStream ->
            Yaml(c1).loadAll<Person>(inputStream).forEach {
                item: Person -> personRepository.save(item)
            }
        }
    }

    private fun <T> Yaml.loadAll(inputStream: InputStream): Iterable<T> =
        this.loadAll(inputStream) as Iterable<T>
}
