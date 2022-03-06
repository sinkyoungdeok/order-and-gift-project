package msa.order.domain.user

interface UserService {
    suspend fun registerUser(
        command: UserCommand.RegisterUserRequest
    ): UserInfo.Main

    suspend fun registerAdmin(
        command: UserCommand.RegisterAdminRequest
    ): UserInfo.Main

    suspend fun registerPartner(
        command: UserCommand.RegisterPartnerRequest
    ): UserInfo.MainWithId

    suspend fun retrieveUser(
        username: String
    ): UserInfo.Main

    suspend fun retrieveUserWithPassword(
        username: String
    ): UserInfo.MainWithPassword

    suspend fun quitUser(
        username: String
    ): UserInfo.Main

    suspend fun comeBackUser(
        username: String
    ): UserInfo.Main
}