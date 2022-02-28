package msa.order.domain.user


class UserCommand {
    class RegisterUserRequest(
        var username: String,
        var password: String,
        var roleList: List<RegisterRoleRequest>? = null
    )

    class RegisterRoleRequest(
        var role: String
    )
}