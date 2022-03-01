package msa.gift.interfaces.user

import javax.validation.constraints.NotEmpty

class UserDto {

    class RegisterUserRequest(
        @field:NotEmpty(message = "username은 필수값 입니다")
        var username: String,
        @field:NotEmpty(message = "password는 필수값 입니다")
        var password: String
    )

    class RegisterUserResponse(
        var username: String,
        var roleList: List<RegisterRoleResponse>? = null
    )

    class RegisterRoleResponse(
        var role: String
    )
}