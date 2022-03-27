package msa.order.domain.auth

import msa.order.common.exception.InvalidPasswordException
import msa.order.common.exception.InvalidTokenException
import msa.order.common.jwt.JwtUtil
import msa.order.common.jwt.PBKDF2Encoder
import msa.order.domain.user.UserCommand
import msa.order.domain.user.UserInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(
    val jwtUtil: JwtUtil,
    val passwordEncoder: PBKDF2Encoder
) : AuthService {

    @Transactional(readOnly = true)
    override fun login(userInfo: UserInfo.MainWithPassword, password: String): UserInfo.Token {
        if (!passwordEncoder.encode(password)
                .equals(userInfo.password)
        ) throw InvalidPasswordException("비밀번호가 틀렸습니다")

        return jwtUtil.generateToken(userInfo)
    }

    @Transactional(readOnly = true)
    override fun reissue(command: UserCommand.ReissueTokenRequest): UserInfo.Token {
        if (!jwtUtil.validateToken(command.refreshToken))
            throw InvalidTokenException()

        return jwtUtil.reissueToken(command)
    }
}