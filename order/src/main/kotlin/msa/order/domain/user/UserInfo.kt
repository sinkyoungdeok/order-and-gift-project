package msa.order.domain.user


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
    }

    class RoleInfo(
        var role: String
    )
}