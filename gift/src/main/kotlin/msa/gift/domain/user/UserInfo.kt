package msa.gift.domain.user

class UserInfo {
    class Main(
        var username: String,
        var password: String,
        var roleList: List<RoleInfo>? = null
    ) {
        constructor() : this(
            "",
            "",
            null
        )

        constructor(user: User) : this(
            user.username,
            user.password,
            user.roles.map { RoleInfo(it.toString()) }
        )
    }

    class RoleInfo(
        var role: String
    )
}