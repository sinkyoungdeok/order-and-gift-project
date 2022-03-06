package msa.order.application.user

import msa.order.domain.user.UserCommand
import msa.order.domain.user.UserInfo
import msa.order.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class UserFacade(
    val userService: UserService
) {
    suspend fun registerUser(command: UserCommand.RegisterUserRequest): UserInfo.Main {
        val userInfo = userService.registerUser(command)
        return userInfo
    }

    suspend fun retrieveUser(loginId: String): UserInfo.Main {
        val userInfo = userService.retrieveUser(loginId)
        return userInfo
    }

    suspend fun quitUser(loginId: String): UserInfo.Main {
        val userInfo = userService.quitUser(loginId)
        return userInfo
    }

    suspend fun comeBackUser(loginId: String): UserInfo.Main {
        val userInfo = userService.comeBackUser(loginId)
        return userInfo
    }
}