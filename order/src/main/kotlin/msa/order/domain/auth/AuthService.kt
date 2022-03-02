package msa.order.domain.auth

import msa.order.domain.user.UserInfo

interface AuthService {
    fun login(
        userInfo: UserInfo.MainWithPassword,
        password: String
    ): String
}