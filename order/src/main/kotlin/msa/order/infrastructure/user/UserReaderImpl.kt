package msa.order.infrastructure.user

import kotlinx.coroutines.reactor.awaitSingleOrNull
import msa.order.domain.user.User
import msa.order.domain.user.UserReader
import org.springframework.stereotype.Component

@Component
class UserReaderImpl(
    val userRepository: UserRepository
) : UserReader {
    override suspend fun getUserBy(username: String): User? {
        return userRepository.findByUsername(username)
            .awaitSingleOrNull()
    }
}