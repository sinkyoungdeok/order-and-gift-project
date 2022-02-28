package msa.gift.common.jwt

import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class SecurityContextRepository(
    val authenticationManager: AuthenticationManager
) : ServerSecurityContextRepository {
    override fun save(swe: ServerWebExchange, sc: SecurityContext): Mono<Void> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun load(swe: ServerWebExchange): Mono<SecurityContext> {
        return Mono.justOrEmpty(swe.request.headers.getFirst(HttpHeaders.AUTHORIZATION))
            .filter { authHeader: String ->
                authHeader.startsWith(
                    "Bearer "
                )
            }
            .flatMap { authHeader: String ->
                val authToken = authHeader.substring(7)
                val auth: Authentication =
                    UsernamePasswordAuthenticationToken(authToken, authToken)
                authenticationManager.authenticate(auth).map { SecurityContextImpl(it) }
            }
    }
}