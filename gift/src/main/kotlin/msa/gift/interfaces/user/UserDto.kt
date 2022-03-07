package msa.gift.interfaces.user

import javax.validation.constraints.NotEmpty

class UserDto {

    class RegisterUserRequest(
        @field:NotEmpty(message = "loginId은 필수값 입니다")
        var loginId: String,
        @field:NotEmpty(message = "password는 필수값 입니다")
        var password: String
    )

    class RegisterUserResponse(
        var loginId: String,
        var enabled: Boolean,
        var roleList: List<RegisterRoleResponse>? = null
    )

    class RegisterRoleResponse(
        var role: String
    )
}