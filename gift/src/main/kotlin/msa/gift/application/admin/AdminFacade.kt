package msa.gift.application.admin

import msa.gift.domain.user.UserCommand
import msa.gift.domain.user.UserInfo
import msa.gift.domain.user.UserService
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