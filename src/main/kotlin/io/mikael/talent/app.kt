package io.mikael.talent

import io.mikael.talent.util.JwtConfiguration
import io.mikael.talent.util.JwtFilter
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

@SpringBootApplication
class Application

@Configuration
class DataRestAuthentication(val jwt: JwtConfiguration) {

    @Bean
    fun jwtFilter() = JwtFilter(jwt.secret, jwt.issuer)

}

@Controller
class SinglePageController {

    @GetMapping("/", "/app", "/app/", "/app/**", "/login", "/register")
    fun singlePageApplication() = "forward:/index.html"

}
