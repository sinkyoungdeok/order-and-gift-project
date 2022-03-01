package msa.gift.infrastructure.user

import kotlinx.coroutines.reactor.awaitSingle
import msa.gift.domain.user.User
import msa.gift.domain.user.UserStore
import msa.gift.infrastructure.user.UserRepository
import org.springframework.stereotype.Component

@Component
class UserStoreImpl(
    val userRepository: UserRepository
) : UserStore {
    override suspend fun store(user: User): User {
        return userRepository.save(user).awaitSingle()
    }
}