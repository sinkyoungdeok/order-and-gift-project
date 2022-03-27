package msa.order.common.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import msa.order.domain.user.UserInfo
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*
import javax.annotation.PostConstruct

@Component
class JwtUtil {
    @Value("\${springbootwebfluxjjwt.jjwt.secret}")
    private lateinit var secret: String

    @Value("\${springbootwebfluxjjwt.jjwt.access-token-validity-in-seconds}")
    private lateinit var accessTokenExpirationTime: String

    @Value("\${springbootwebfluxjjwt.jjwt.refresh-token-validity-in-seconds}")
    private lateinit var refreshTokenExpirationTime: String
    private lateinit var key: Key

    @PostConstruct
    fun init() {
        key = Keys.hmacShaKeyFor(secret.toByteArray())
    }

    fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).body
    }

    fun getUsernameFromToken(token: String?): String {
        return getAllClaimsFromToken(token).subject
    }

    fun getExpirationDateFromToken(token: String?): Date {
        return getAllClaimsFromToken(token).expiration
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(userInfo: UserInfo.MainWithPassword): UserInfo.Token {
        val claims: MutableMap<String, Any?> = HashMap()
        claims["role"] = userInfo.roles
        return doGenerateToken(claims, userInfo.loginId)
    }

    private fun doGenerateToken(claims: Map<String, Any?>, username: String): UserInfo.Token {
        val accessTokenExpirationTimeLong = accessTokenExpirationTime.toLong()
        val refreshTokenExpirationTimeLong = refreshTokenExpirationTime.toLong()
        val createdDate = Date()
        val accessTokenExpirationDate =
            Date(createdDate.time + accessTokenExpirationTimeLong * 1000)
        val refreshTokenExpirationDate =
            Date(createdDate.time + refreshTokenExpirationTimeLong * 1000)

        val accessToken = Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(createdDate)
            .setExpiration(accessTokenExpirationDate)
            .signWith(key)
            .compact()
        val refreshToken = Jwts.builder()
            .setClaims(claims)
            .setSubject(username)
            .setIssuedAt(createdDate)
            .setExpiration(refreshTokenExpirationDate)
            .signWith(key)
            .compact()

        return UserInfo.Token(
            accessToken,
            refreshToken,
            "Bearer",
            accessTokenExpirationTimeLong,
            createdDate
        )
    }

    fun validateToken(token: String): Boolean {
        return !isTokenExpired(token)
    }
}
