package msa.gift.infrastructure

import msa.gift.domain.user.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : ReactiveMongoRepository<User, String> {
}