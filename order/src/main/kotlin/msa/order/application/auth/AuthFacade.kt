package msa.order.application.auth

import msa.order.domain.auth.AuthService
import msa.order.domain.user.UserInfo
import msa.order.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class AuthFacade(
    val userService: UserService,
    val authService: AuthService
) {

    suspend fun login(loginId: String, password: String): UserInfo.Token {
        val userInfo = userService.retrieveUserWithPassword(loginId)
        val token = authService.login(userInfo, password)
        return token
    }
}