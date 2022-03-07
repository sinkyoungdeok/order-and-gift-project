package msa.gift.domain.user

class UserInfo {
    class Main(
        var loginId: String,
        var enabled: Boolean,
        var roleList: List<RoleInfo>? = null
    ) {
        constructor() : this(
            "",
            true,
            null
        )

        constructor(user: User) : this(
            user.loginId,
            user.enabled,
            user.roles.map { RoleInfo(it.toString()) }
        )
    }

    class MainWithPassword(
        var loginId: String,
        var password: String,
        var enabled: Boolean,
        var roles: List<Role>
    ) {
        constructor(user: User) : this(
            user.loginId,
            user.password,
            user.enabled,
            user.roles
        )
    }


    class RoleInfo(
        var role: String
    )
}