package msa.gift.infrastructure.gift

import msa.gift.domain.gift.Gift
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface GiftRepository : ReactiveMongoRepository<Gift, String> {
}