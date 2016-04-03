package io.mikael.talent

import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.BeanFactory
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2SsoProperties
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.client.ClientHttpResponse
import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.client.OAuth2RestOperations
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher
import org.springframework.web.accept.ContentNegotiationStrategy
import org.springframework.web.accept.HeaderContentNegotiationStrategy
import org.springframework.web.client.ResponseExtractor
import java.io.IOException
import java.util.*

class YammerSsoSecurityConfigurer(private val beanFactory: BeanFactory) {

    @Throws(Exception::class)
    fun configure(http: HttpSecurity) {
        val sso = this.beanFactory.getBean(OAuth2SsoProperties::class.java)
        http.apply(OAuth2ClientAuthenticationConfigurer(oauth2SsoFilter(sso)))
        addAuthenticationEntryPoint(http, sso)
    }

    @Throws(Exception::class)
    private fun addAuthenticationEntryPoint(http: HttpSecurity, sso: OAuth2SsoProperties) {
        val exceptions = http.exceptionHandling()
        var contentNegotiationStrategy: ContentNegotiationStrategy? = http.getSharedObject(ContentNegotiationStrategy::class.java)
        if (contentNegotiationStrategy == null) {
            contentNegotiationStrategy = HeaderContentNegotiationStrategy()
        }
        val preferredMatcher = MediaTypeRequestMatcher(
            contentNegotiationStrategy, MediaType.APPLICATION_XHTML_XML,
            MediaType("image", "*"), MediaType.TEXT_HTML, MediaType.TEXT_PLAIN)
        preferredMatcher.setIgnoredMediaTypes(setOf(MediaType.ALL))
        exceptions.defaultAuthenticationEntryPointFor(
            LoginUrlAuthenticationEntryPoint(sso.loginPath),
            preferredMatcher)
        // When multiple entry points are provided the default is the first one
        exceptions.defaultAuthenticationEntryPointFor(
            HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
            RequestHeaderRequestMatcher("X-Requested-With", "XMLHttpRequest"))
    }

    private fun oauth2SsoFilter(
        sso: OAuth2SsoProperties): OAuth2ClientAuthenticationProcessingFilter {
        val restTemplate = this.beanFactory.getBean(OAuth2RestOperations::class.java)
        val tokenServices = this.beanFactory.getBean(ResourceServerTokenServices::class.java)
        val filter = OAuth2ClientAuthenticationProcessingFilter(sso.loginPath)
        filter.setRestTemplate(restTemplate)
        filter.setTokenServices(tokenServices)
        return filter
    }

    class OAuth2ClientAuthenticationConfigurer(private val filter: OAuth2ClientAuthenticationProcessingFilter) : SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>() {

        @Throws(Exception::class)
        override fun configure(builder: HttpSecurity?) {
            val ssoFilter = this.filter
            ssoFilter.setSessionAuthenticationStrategy(builder!!.getSharedObject(SessionAuthenticationStrategy::class.java))
            builder.addFilterAfter(ssoFilter, AbstractPreAuthenticatedProcessingFilter::class.java)
        }

    }

}

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
        return DefaultOAuth2AccessToken("").apply {
            tokenType = DefaultOAuth2AccessToken.BEARER_TYPE
            value = ctx.read("$.access_token.token", String::class.java)
            expiration = ctx.read("$.access_token.expires_at", Date::class.java)
            additionalInformation = mapOf("user" to ctx.read("$.user", Map::class.java),
                "network" to ctx.read("$.network", Map::class.java))
        }
    }
}
