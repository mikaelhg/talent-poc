package io.mikael.talent

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.*

@SpringBootApplication
open class Application {

}

@Controller
open class TemplateController {

    @RequestMapping("/", method = arrayOf(GET))
    fun index() = "index"

    @RequestMapping("/login", method = arrayOf(GET))
    fun login() = "login"

    @RequestMapping("/project/{id}", method = arrayOf(GET))
    fun project(@PathVariable("id") id: Int) = "project"

    @RequestMapping("/{username}", method = arrayOf(GET))
    fun person(@PathVariable("username") id: String) = "person"

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
