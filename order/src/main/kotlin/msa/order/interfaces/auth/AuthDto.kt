package msa.order.interfaces.auth

import java.util.*

class AuthDto {

    class LoginRequest(
        var loginId: String,
        var password: String
    )

    class LoginResponse(
        var accessToken: String,
        var refreshToken: String,
        var tokenType: String,
        var expiresIn: Long,
        var issuedAt: Date
    )

    class ReissueRequest(
        var accessToken: String,
        var refreshToken: String
    )
}