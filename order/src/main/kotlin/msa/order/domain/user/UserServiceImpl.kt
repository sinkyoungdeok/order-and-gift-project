package msa.order.domain.user

import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override suspend fun registerUser(command: UserCommand.RegisterUserRequest): UserInfo.Main {
        TODO("Not yet implemented")
    }
}