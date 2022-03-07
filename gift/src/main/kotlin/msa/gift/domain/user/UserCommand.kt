package msa.gift.domain.user

import msa.gift.common.jwt.PBKDF2Encoder
import msa.gift.common.util.TokenGenerator


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
}