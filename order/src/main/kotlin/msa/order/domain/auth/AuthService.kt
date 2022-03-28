package msa.order.domain.auth

import msa.order.domain.user.UserCommand
import msa.order.domain.user.UserInfo

interface AuthService {
    fun login(
        userInfo: UserInfo.MainWithPassword,
        password: String
    ): UserInfo.Token

    fun reissue(
        userCommand: UserCommand.ReissueTokenRequest
    ): UserInfo.Token

    fun logout(
        loginId: String
    )
}