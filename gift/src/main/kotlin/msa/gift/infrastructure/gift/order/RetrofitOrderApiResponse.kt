package msa.gift.infrastructure.gift.order

import java.util.*

class RetrofitOrderApiResponse {
    class RegisterOrder(
        var orderToken: String
    )

    class LoginResponse(
        var accessToken: String,
        var refreshToken: String,
        var tokenType: String,
        var expiresIn: Long,
        var issuedAt: Date
    )
}