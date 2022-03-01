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

    suspend fun retrieveUser(username: String): UserInfo.Main {
        val userInfo = userService.retrieveUser(username)
        return userInfo
    }
}