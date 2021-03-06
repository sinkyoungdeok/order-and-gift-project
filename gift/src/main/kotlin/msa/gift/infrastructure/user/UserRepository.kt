package msa.gift.infrastructure.user

import msa.gift.domain.user.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveMongoRepository<User, String> {
    fun findByLoginId(loginId: String): Mono<User>
}