package msa.gift.application.auth

import msa.gift.domain.auth.AuthService
import msa.gift.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class AuthFacade(
    val userService: UserService,
    val authService: AuthService
) {

    suspend fun login(username: String, password: String): String {
        val userInfo = userService.retrieveUserWithPassword(username)
        val token = authService.login(userInfo, password)
        return token
    }
}