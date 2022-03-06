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
        loginId: String
    ): UserInfo.Main

    suspend fun retrieveUserWithPassword(
        loginId: String
    ): UserInfo.MainWithPassword

    suspend fun quitUser(
        loginId: String
    ): UserInfo.Main

    suspend fun comeBackUser(
        loginId: String
    ): UserInfo.Main
}