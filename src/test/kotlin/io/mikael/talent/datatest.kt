package io.mikael.talent

import io.mikael.talent.model.Person
import org.junit.Ignore
import org.junit.Test
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

class DataTest {

    @Test
    @Ignore
    fun readData() {

        val c1 = Constructor(Person::class.java)

        file("data.yml").use { inputStream ->
            Yaml(c1).loadAll(inputStream).forEach { item ->
                println(item)
            }
        }

    }

    private fun file(name: String) = javaClass.classLoader.getResourceAsStream(name)

}
