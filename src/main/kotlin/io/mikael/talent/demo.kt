package io.mikael.talent

import io.mikael.talent.model.Person
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.core.env.Environment
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

@Component
open class DemoDataPopulator : ApplicationRunner {

    @Value("classpath:/data.yml")
    private lateinit var dataFile: Resource

    @Autowired
    private lateinit var env: Environment

    @Autowired
    private lateinit var personRepository: PersonRepository

    companion object {
        private val pc = Constructor(Person::class.java)
    }

    override fun run(args: ApplicationArguments) {
        if (env.activeProfiles.contains("prod")) {
            return
        }
        dataFile.inputStream.use {
            Yaml(pc).loadAll(it)
                .map { it as Person }
                .map(personRepository::save)
        }
    }

}
