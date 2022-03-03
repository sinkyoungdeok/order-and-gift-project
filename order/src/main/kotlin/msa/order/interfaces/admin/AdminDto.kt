package msa.order.interfaces.admin

import javax.validation.constraints.NotEmpty

class AdminDto {

    class RegisterAdminRequest(
        @field:NotEmpty(message = "username은 필수값 입니다")
        var username: String,
        @field:NotEmpty(message = "password는 필수값 입니다")
        var password: String
    )

    class RegisterAdminResponse(
        var username: String,
        var enabled: Boolean,
        var roleList: List<RegisterRoleResponse>? = null
    )

    class RegisterRoleResponse(
        var role: String
    )
}