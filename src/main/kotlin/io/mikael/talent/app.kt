package io.mikael.talent

import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoDefaultConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.servlet.ModelAndView
import java.security.Principal
import org.springframework.web.bind.annotation.PathVariable as path
import org.springframework.web.bind.annotation.RequestMapping as http

@SpringBootApplication
@EnableOAuth2Sso
open class Application : OAuth2SsoDefaultConfiguration() {

    @Autowired
    open var myBeanFactory: BeanFactory? = null

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
            .regexMatchers("/", "/favicon.ico", "/webjars.*", "/static.*").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout().logoutRequestMatcher(AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")

        YammerSsoSecurityConfigurer(this.myBeanFactory!!).configure(http)
    }

    @Autowired
    open fun customizeOAuth2RestTemplate(restTemplate: OAuth2RestTemplate) {
        restTemplate.setAccessTokenProvider(YammerAuthorizationCodeAccessTokenProvider())
    }

}

@Controller
open class WebUserInterface {

    @http("/", method = arrayOf(GET))
    fun whatsnew(auth: OAuth2Authentication?) = ModelAndView("index", "auth", auth)

    @http("/project/{id}", method = arrayOf(GET))
    fun project(@path id: Int) = ModelAndView("project", "id", id)

    @http("/{username}", method = arrayOf(GET))
    fun person(auth: OAuth2Authentication, @path username: String) =
        ModelAndView("person", mapOf("auth" to auth, "username" to username))

    @http(value = "/user", method = arrayOf(GET))
    @ResponseBody
    fun user(principal: Principal) = principal

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
