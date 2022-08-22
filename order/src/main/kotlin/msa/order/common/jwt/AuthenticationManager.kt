package msa.order.common.jwt

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.stream.Collectors

@Component
class AuthenticationManager(
    val jwtUtil: JwtUtil
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val authToken = authentication.credentials.toString()
        var username: String?
        try {
            username = jwtUtil.getUsernameFromToken(authToken)
        } catch (e: Exception) {
            return Mono.empty()
        }

        return Mono.just(jwtUtil.validateToken(authToken))
            .filter { valid -> valid }
            .switchIfEmpty(Mono.empty())
            .map {
                val claims = jwtUtil.getAllClaimsFromToken(authToken)
                val rolesMap = claims.get("role", java.util.List::class.java)
                UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    rolesMap.stream()
                        .map { role ->
                            SimpleGrantedAuthority(
                                role as String
                            )
                        }
                        .collect(Collectors.toList())
                )
            }
    }
}