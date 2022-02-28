package msa.order.domain.user

import msa.order.common.jwt.PBKDF2Encoder
import msa.order.common.util.TokenGenerator


class UserCommand {
    class RegisterUserRequest(
        var username: String,
        var password: String,
        var roleList: List<RegisterRoleRequest> = mutableListOf()
    ) {
        private val USER_PREFIX = "user_"

        fun toEntity(pbkdF2Encoder: PBKDF2Encoder): User {
            return User(
                username,
                pbkdF2Encoder.encode(password),
                true,
                roleList.map { it.toEntity() },
                TokenGenerator.randomCharacterWithPrefix(USER_PREFIX) ?: ""
            )
        }
    }

    class RegisterRoleRequest(
        var role: String
    ) {
        fun toEntity(): Role {
            if (role == "ROLE_USER") {
                return Role.ROLE_USER
            }
            if (role == "ROLE_ADMIN") {
                return Role.ROLE_ADMIN
            }
            if (role == "ROLE_PARTNER") {
                return Role.ROLE_PARTNER
            }
            return Role.ROLE_USER
        }
    }
}