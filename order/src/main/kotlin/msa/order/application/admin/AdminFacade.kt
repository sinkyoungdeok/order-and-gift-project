package msa.order.application.admin

import msa.order.domain.user.UserCommand
import msa.order.domain.user.UserInfo
import msa.order.domain.user.UserService
import org.springframework.stereotype.Service

@Service
class AdminFacade(
    val userService: UserService
) {
    suspend fun registerAdmin(command: UserCommand.RegisterAdminRequest): UserInfo.Main {
        val userInfo = userService.registerAdmin(command)
        return userInfo
    }
}