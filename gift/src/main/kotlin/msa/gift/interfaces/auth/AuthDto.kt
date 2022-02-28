package msa.gift.interfaces.auth

class AuthDto {

    class LoginRequest(
        var username: String,
        var password: String
    )

    class LoginResponse(
        var token: String
    )
}