package msa.gift.application.user

import msa.gift.domain.user.UserCommand
import msa.gift.domain.user.UserInfo
import msa.gift.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class UserFacade(
    val userService: UserService
) {
    suspend fun registerUser(command: UserCommand.RegisterUserRequest): UserInfo.Main {
        val userInfo = userService.registerUser(command)
        return userInfo
    }

    suspend fun retrieveUser(logIn: String): UserInfo.Main {
        val userInfo = userService.retrieveUser(logIn)
        return userInfo
    }

    suspend fun quitUser(logIn: String): UserInfo.Main {
        val userInfo = userService.quitUser(logIn)
        return userInfo
    }

    suspend fun comeBackUser(logIn: String): UserInfo.Main {
        val userInfo = userService.comeBackUser(logIn)
        return userInfo
    }
}