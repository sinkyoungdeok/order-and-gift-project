package msa.gift.domain.auth

import msa.gift.common.exception.InvalidPasswordException
import msa.gift.common.exception.InvalidTokenException
import msa.gift.common.jwt.JwtUtil
import msa.gift.common.jwt.PBKDF2Encoder
import msa.gift.domain.user.UserCommand
import msa.gift.domain.user.UserInfo
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