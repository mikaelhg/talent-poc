package io.mikael.talent

import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.AutoConfigureBefore
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration
import org.springframework.boot.autoconfigure.security.oauth2.method.OAuth2MethodSecurityConfiguration
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerConfiguration
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.*
import org.springframework.http.client.ClientHttpResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2ClientContext
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails
import org.springframework.security.oauth2.client.token.AccessTokenRequest
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.web.client.ResponseExtractor
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import java.io.IOException
import java.util.*
import javax.annotation.Resource

class YammerAuthorizationCodeAccessTokenProvider : AuthorizationCodeAccessTokenProvider() {
    override fun getResponseExtractor(): ResponseExtractor<OAuth2AccessToken> {
        super.getResponseExtractor()
        return YammerOAuth2AccessTokenExtractor()
    }
}

class YammerOAuth2AccessTokenExtractor : ResponseExtractor<OAuth2AccessToken> {
    @Throws(IOException::class)
    override fun extractData(response: ClientHttpResponse): OAuth2AccessToken {
        val ctx = JsonPath.parse(response.body)
        val accessToken = DefaultOAuth2AccessToken(ctx.read("$.access_token.token", String::class.java))
        accessToken.expiration = ctx.read("$.access_token.expires_at", Date::class.java)
        accessToken.tokenType = DefaultOAuth2AccessToken.BEARER_TYPE
        val additionalInformation = HashMap<String, Any>()
        additionalInformation.put("user", ctx.read("$.user", Map::class.java))
        additionalInformation.put("network", ctx.read("$.network", Map::class.java))
        accessToken.additionalInformation = additionalInformation
        return accessToken
    }
}

@Configuration
@ConditionalOnClass(OAuth2AccessToken::class, WebMvcConfigurerAdapter::class)
@Import(OAuth2AuthorizationServerConfiguration::class, OAuth2MethodSecurityConfiguration::class, OAuth2ResourceServerConfiguration::class, YammerOAuth2RestOperationsConfiguration::class)
@AutoConfigureBefore(WebMvcAutoConfiguration::class)
@EnableConfigurationProperties(OAuth2ClientProperties::class)
open class YammerOAuth2AutoConfiguration {

    @Autowired
    private val credentials: OAuth2ClientProperties? = null

    @Bean
    open fun resourceServerProperties(): ResourceServerProperties {
        return ResourceServerProperties(this.credentials!!.clientId, this.credentials.clientSecret)
    }

}

/**
 * Basically copy-pasted from the Spring Boot OAuth2RestOperationsConfiguration class,
 * since it doesn't lend itself to customization.

 * The only thing we need to do here, is to add a custom AccessTokenProvider to the
 * local OAuth2RestTemplate.

 * All else is superfluous and likely to break when Spring Boot changes.
 */
@Configuration
open class YammerOAuth2RestOperationsConfiguration {

    @Bean
    @Primary
    open fun oauth2RestTemplate(oauth2ClientContext: OAuth2ClientContext, details: OAuth2ProtectedResourceDetails): OAuth2RestTemplate {
        val template = OAuth2RestTemplate(details, oauth2ClientContext)
        template.setAccessTokenProvider(YammerAuthorizationCodeAccessTokenProvider())
        return template
    }

    @Configuration
    abstract class BaseConfiguration {

        @Bean
        @ConfigurationProperties("security.oauth2.client")
        @Primary
        open fun oauth2RemoteResource(): AuthorizationCodeResourceDetails {
            return AuthorizationCodeResourceDetails()
        }

    }

    @Configuration
    @ConditionalOnNotWebApplication
    open class SingletonScopedConfiguration {

        @Bean
        @ConfigurationProperties("security.oauth2.client")
        @Primary
        open fun oauth2RemoteResource(): ClientCredentialsResourceDetails {
            return ClientCredentialsResourceDetails()
        }

        @Bean
        open fun oauth2ClientContext(): DefaultOAuth2ClientContext {
            return DefaultOAuth2ClientContext(DefaultAccessTokenRequest())
        }

    }

    @Configuration
    open class SessionScopedConfiguration : BaseConfiguration() {

        @Bean
        open fun oauth2ClientFilterRegistration(filter: OAuth2ClientContextFilter): FilterRegistrationBean {
            val registration = FilterRegistrationBean()
            registration.setFilter(filter)
            registration.order = -100
            return registration
        }

        @Configuration
        open protected class ClientContextConfiguration {

            @Resource
            @Qualifier("accessTokenRequest")
            protected var accessTokenRequest: AccessTokenRequest? = null

            @Bean
            @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
            open fun oauth2ClientContext(): DefaultOAuth2ClientContext {
                return DefaultOAuth2ClientContext(this.accessTokenRequest)
            }

        }

    }

    @Configuration
    open class RequestScopedConfiguration : BaseConfiguration() {

        @Bean
        @Scope(value = "request", proxyMode = ScopedProxyMode.INTERFACES)
        open fun oauth2ClientContext(): DefaultOAuth2ClientContext {
            val context = DefaultOAuth2ClientContext(DefaultAccessTokenRequest())
            val principal = SecurityContextHolder.getContext().authentication
            if (principal is OAuth2Authentication) {
                val details = principal.details
                if (details is OAuth2AuthenticationDetails) {
                    val token = details.tokenValue
                    context.accessToken = DefaultOAuth2AccessToken(token)
                }
            }
            return context
        }

    }

}
