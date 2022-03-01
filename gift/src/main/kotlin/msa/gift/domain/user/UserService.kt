package msa.gift.domain.user

interface UserService {
    suspend fun registerUser(
        command: UserCommand.RegisterUserRequest
    ): UserInfo.Main

    suspend fun retrieveUser(
        username: String
    ): UserInfo.Main

    suspend fun quitUser(
        username: String
    ): UserInfo.Main
}