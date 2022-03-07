package msa.gift.infrastructure.user

import kotlinx.coroutines.reactor.awaitSingle
import msa.gift.domain.user.Role
import msa.gift.domain.user.User
import msa.gift.domain.user.UserStore
import org.springframework.stereotype.Component
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.*
import javax.annotation.PostConstruct

@Component
class UserStoreImpl(
    val userRepository: UserRepository
) : UserStore {
    override suspend fun store(user: User): User {
        return userRepository.save(user).awaitSingle()
    }

    @PostConstruct
    fun init() {
        val user = User(
            "유저1",
            "user",
            "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=",
            true,
            Arrays.asList(Role.ROLE_USER)
        )

        val admin = User(
            "어드민1",
            "admin",
            "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=",
            true,
            Arrays.asList(Role.ROLE_ADMIN)
        )

        val partner = User(
            "파트너1",
            "partner",
            "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=",
            true,
            Arrays.asList(Role.ROLE_PARTNER)
        )

        userRepository.findByLoginId("user")
            .switchIfEmpty { userRepository.save(user) }
            .block()
        userRepository.findByLoginId("admin")
            .switchIfEmpty { userRepository.save(admin) }
            .block()
        userRepository.findByLoginId("partner")
            .switchIfEmpty { userRepository.save(partner) }
            .block()
    }
}