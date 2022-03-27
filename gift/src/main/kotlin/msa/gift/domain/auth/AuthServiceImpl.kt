package msa.gift.domain.auth

import msa.gift.common.exception.InvalidPasswordException
import msa.gift.common.jwt.JwtUtil
import msa.gift.common.jwt.PBKDF2Encoder
import msa.gift.domain.user.UserInfo
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    val jwtUtil: JwtUtil,
    val passwordEncoder: PBKDF2Encoder
) : AuthService {

    override fun login(userInfo: UserInfo.MainWithPassword, password: String): UserInfo.Token {
        if (!passwordEncoder.encode(password)
                .equals(userInfo.password)
        ) throw InvalidPasswordException("비밀번호가 틀렸습니다")

        return jwtUtil.generateToken(userInfo)
    }
}