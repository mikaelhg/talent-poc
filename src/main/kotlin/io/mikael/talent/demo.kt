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
import javax.transaction.Transactional

@Component
class DemoDataPopulator : ApplicationRunner {

    companion object {
        private val log = org.slf4j.LoggerFactory.getLogger(DemoDataPopulator::class.java)
        private val pc = org.yaml.snakeyaml.constructor.Constructor(Person::class.java)
    }

    @Value("classpath:/data.yml")
    private lateinit var dataFile: Resource

    @Autowired
    private lateinit var env: Environment

    @Autowired
    private lateinit var personRepository: PersonRepository

    @Transactional
    override fun run(args: ApplicationArguments) {
        if ("prod" in env.activeProfiles) {
            return
        }
        log.info("Inserting some demo data...")
        dataFile.inputStream.use {
            Yaml(pc).loadAll(it)
                .map { it as Person }
                .onEach { log.info("$it") }
                .map(personRepository::save)
        }
    }

}
