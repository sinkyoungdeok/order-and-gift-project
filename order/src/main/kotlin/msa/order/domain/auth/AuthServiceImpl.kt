package msa.order.domain.auth

import msa.order.common.exception.InvalidPasswordException
import msa.order.common.exception.InvalidTokenException
import msa.order.common.jwt.JwtUtil
import msa.order.common.jwt.PBKDF2Encoder
import msa.order.domain.user.UserCommand
import msa.order.domain.user.UserInfo
import msa.order.infrastructure.redis.RedisRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

@Service
class AuthServiceImpl(
    val jwtUtil: JwtUtil,
    val passwordEncoder: PBKDF2Encoder,
    val redisRepository: RedisRepository
) : AuthService {

    @Value("\${springbootwebfluxjjwt.jjwt.refresh-token-validity-in-seconds}")
    private lateinit var refreshTokenExpirationTime: String

    @Transactional(readOnly = true)
    override fun login(userInfo: UserInfo.MainWithPassword, password: String): UserInfo.Token {
        if (!passwordEncoder.encode(password)
                .equals(userInfo.password)
        ) throw InvalidPasswordException("비밀번호가 틀렸습니다")
        val token = jwtUtil.generateToken(userInfo)

        redisRepository.setValue(
            "RT:"+ userInfo.loginId,
            token.refreshToken,
            refreshTokenExpirationTime.toLong(),
            TimeUnit.MILLISECONDS
        )
        return token
    }

    @Transactional(readOnly = true)
    override fun reissue(command: UserCommand.ReissueTokenRequest): UserInfo.Token {
        if (!jwtUtil.validateToken(command.refreshToken))
            throw InvalidTokenException()

        val userId = jwtUtil.getUsernameFromToken(command.refreshToken)
        val refreshToken = redisRepository.getValue("RT:$userId")
            ?: throw InvalidTokenException()
        if (!refreshToken.equals(command.refreshToken))
            throw InvalidTokenException()

        val token = jwtUtil.reissueToken(command)

        redisRepository.setValue(
            "RT:$userId",
            token.refreshToken,
            refreshTokenExpirationTime.toLong(),
            TimeUnit.MILLISECONDS
        )
        return token
    }
}