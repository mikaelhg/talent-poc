package io.mikael.talent

import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoDefaultConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
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
        http.antMatcher("/**").authorizeRequests().anyRequest().authenticated()
        YammerSsoSecurityConfigurer(this.myBeanFactory!!).configure(http)
    }

    @Autowired
    open fun customizeOAuth2RestTemplate(restTemplate: OAuth2RestTemplate) {
        restTemplate.setAccessTokenProvider(YammerAuthorizationCodeAccessTokenProvider())
    }

}

@Controller
open class TemplateController {

    @http("/", method = arrayOf(GET))
    fun index() = view("index")

    @http("/zlogin", method = arrayOf(GET))
    fun login() = view("login")

    @http("/project/{id}", method = arrayOf(GET))
    fun project(@path id: Int): ModelAndView {
        return view("project", "id", id)
    }

    @http("/{username}", method = arrayOf(GET))
    fun person(@path username: String) = view("person", "username", username)

    @http(value = "/user", method = arrayOf(GET))
    @ResponseBody
    fun user(principal: Principal): Principal {
        return principal
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}

private fun view(viewName: String, model: ModelMap = ModelMap()) = ModelAndView(viewName, model)

private fun view(viewName: String, key: String, value: Any) = ModelAndView(viewName, key, value)
