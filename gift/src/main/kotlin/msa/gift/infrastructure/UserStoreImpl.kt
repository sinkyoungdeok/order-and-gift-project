package msa.gift.infrastructure

import kotlinx.coroutines.reactor.awaitSingle
import msa.gift.domain.user.User
import msa.gift.domain.user.UserStore
import org.springframework.stereotype.Component

@Component
class UserStoreImpl(
    val userRepository: UserRepository
) : UserStore {
    override suspend fun store(user: User): User {
        return userRepository.save(user).awaitSingle()
    }
}