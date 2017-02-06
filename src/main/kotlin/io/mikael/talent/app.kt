package io.mikael.talent

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable as path

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

@SpringBootApplication
open class Application

@Controller
open class SinglePageController {

    @GetMapping("/", "/app", "/app/", "/app/**", "/login", "/register")
    fun singlePageApplication() = "forward:/index.html"

}
