package io.mikael.talent

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.jwt.JwtHelper
import org.springframework.security.jwt.crypto.sign.SignatureVerifier
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

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

@Configuration
open class DataRestAuthentication {

    @Bean
    open fun jwtFilter(@Value("jwt.key") key: String): Filter = JwtFilter(key)

}

private class JwtFilter(private val key: String) : OncePerRequestFilter() {
//    private val verifier: SignatureVerifier
    init {
//        this.verifier = SignatureVerifier()
    }
    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        val authorizationHeader = req.getHeader(HttpHeaders.AUTHORIZATION)
        if (null != authorizationHeader) {
            val token = authorizationHeader.split(" ")[1]
            req.setAttribute("jwt", JwtHelper.decode(token))
        }
        chain.doFilter(req, res)
    }
}
