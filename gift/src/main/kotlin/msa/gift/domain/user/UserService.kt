package msa.gift.domain.user

interface UserService {
    suspend fun registerUser(
        command: UserCommand.RegisterUserRequest
    ): UserInfo.Main
}