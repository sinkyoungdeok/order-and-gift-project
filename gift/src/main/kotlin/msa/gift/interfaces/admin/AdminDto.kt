package msa.gift.interfaces.admin

import javax.validation.constraints.NotEmpty

class AdminDto {

    class RegisterAdminRequest(
        @field:NotEmpty(message = "loginId은 필수값 입니다")
        var loginId: String,
        @field:NotEmpty(message = "password는 필수값 입니다")
        var password: String
    )

    class RegisterAdminResponse(
        var loginId: String,
        var enabled: Boolean,
        var roleList: List<RegisterRoleResponse>? = null
    )

    class RegisterRoleResponse(
        var role: String
    )
}