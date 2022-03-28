package msa.gift.domain.auth

import msa.gift.common.exception.InvalidPasswordException
import msa.gift.common.exception.InvalidTokenException
import msa.gift.common.jwt.JwtUtil
import msa.gift.common.jwt.PBKDF2Encoder
import msa.gift.domain.user.UserCommand
import msa.gift.domain.user.UserInfo
import msa.gift.infrastructure.redis.RedisRepository
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
    private val ID_PREFIX = "RT:"

    @Transactional(readOnly = true)
    override fun login(userInfo: UserInfo.MainWithPassword, password: String): UserInfo.Token {
        if (!passwordEncoder.encode(password)
                .equals(userInfo.password)
        ) throw InvalidPasswordException("비밀번호가 틀렸습니다")
        val token = jwtUtil.generateToken(userInfo)

        redisRepository.setValue(
            ID_PREFIX + userInfo.loginId,
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

        val loginId = jwtUtil.getUsernameFromToken(command.refreshToken)
        val refreshToken = redisRepository.getValue("$ID_PREFIX$loginId")
            ?: throw InvalidTokenException()
        if (!refreshToken.equals(command.refreshToken))
            throw InvalidTokenException()

        val token = jwtUtil.reissueToken(command)

        redisRepository.setValue(
            "$ID_PREFIX$loginId",
            token.refreshToken,
            refreshTokenExpirationTime.toLong(),
            TimeUnit.MILLISECONDS
        )
        return token
    }

    override fun logout(loginId: String) {
        redisRepository.delValue("$ID_PREFIX$loginId")
    }
}