package io.mikael.talent.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.UnsupportedEncodingException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@ConfigurationProperties(prefix = "jwt")
data class JwtConfiguration(
    @Value("secret") var secret: String,
    @Value("issuer") var issuer: String
)

class JwtFilter(secret: String, issuer: String) : OncePerRequestFilter() {

    companion object {
        const val JWT_KEY = "jwt"
    }

    private val verifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer(issuer).build()

    override fun doFilterInternal(req: HttpServletRequest, res: HttpServletResponse, chain: FilterChain) {
        req.getHeader(HttpHeaders.AUTHORIZATION)?.let {
            try {
                val token = it.split(" ")[1]
                req.setAttribute(JWT_KEY, verifier.verify(token))
            } catch (exception: UnsupportedEncodingException) {
                // UTF-8 encoding not supported
            } catch (exception: JWTVerificationException) {
                // Invalid signature/claims
            } catch (exception: ArrayIndexOutOfBoundsException) {
                // The Authorization header was screwed up
            }
        }
        chain.doFilter(req, res)
    }

}
