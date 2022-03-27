package msa.gift.domain.auth

import msa.gift.domain.user.UserCommand
import msa.gift.domain.user.UserInfo

interface AuthService {
    fun login(
        userInfo: UserInfo.MainWithPassword,
        password: String
    ): UserInfo.Token

    fun reissue(
        userCommand: UserCommand.ReissueTokenRequest
    ): UserInfo.Token
}