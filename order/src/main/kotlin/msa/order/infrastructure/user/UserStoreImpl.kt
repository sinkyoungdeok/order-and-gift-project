package msa.order.infrastructure.user

import kotlinx.coroutines.reactor.awaitSingle
import msa.order.domain.user.User
import msa.order.domain.user.UserStore
import org.springframework.stereotype.Component

@Component
class UserStoreImpl(
    val userRepository: UserRepository
) : UserStore {
    override suspend fun store(user: User): User {
        return userRepository.save(user).awaitSingle()
    }
}