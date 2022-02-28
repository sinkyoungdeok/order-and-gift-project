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
}