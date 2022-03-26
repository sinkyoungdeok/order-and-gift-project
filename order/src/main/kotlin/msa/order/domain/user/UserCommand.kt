package msa.order.domain.user

import msa.order.common.jwt.PBKDF2Encoder
import msa.order.common.util.TokenGenerator


class UserCommand {
    class RegisterUserRequest(
        var loginId: String,
        var password: String
    ) {
        private val USER_PREFIX = "user_"

        fun toEntity(pbkdF2Encoder: PBKDF2Encoder): User {
            return User(
                "",
                loginId,
                pbkdF2Encoder.encode(password),
                true,
                arrayListOf(Role.ROLE_USER),
                TokenGenerator.randomCharacterWithPrefix(USER_PREFIX) ?: ""
            )
        }
    }

    class RegisterAdminRequest(
        var loginId: String,
        var password: String
    ) {
        private val ADMIN_PREFIX = "admin_"

        fun toEntity(pbkdF2Encoder: PBKDF2Encoder): User {
            return User(
                "",
                loginId,
                pbkdF2Encoder.encode(password),
                true,
                arrayListOf(Role.ROLE_ADMIN),
                TokenGenerator.randomCharacterWithPrefix(ADMIN_PREFIX) ?: ""
            )
        }
    }

    class RegisterPartnerRequest(
        var loginId: String,
        var password: String,
        var name: String
    ) {
        private val USER_PREFIX = "user_"

        fun toEntity(pbkdF2Encoder: PBKDF2Encoder): User {
            return User(
                name,
                loginId,
                pbkdF2Encoder.encode(password),
                true,
                arrayListOf(Role.ROLE_PARTNER),
                TokenGenerator.randomCharacterWithPrefix(USER_PREFIX) ?: ""
            )
        }
    }

}