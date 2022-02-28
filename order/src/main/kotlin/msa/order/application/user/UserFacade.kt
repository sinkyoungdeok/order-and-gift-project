package msa.order.application.user

import msa.order.domain.user.UserCommand
import msa.order.domain.user.UserInfo
import org.springframework.stereotype.Service

@Service
class UserFacade(

) {
    suspend fun registerUser(command: UserCommand.RegisterUserRequest): UserInfo.Main {
        return UserInfo.Main()
    }
}