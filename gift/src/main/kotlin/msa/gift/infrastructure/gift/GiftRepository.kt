package msa.gift.infrastructure.gift

import msa.gift.domain.gift.Gift
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface GiftRepository : ReactiveMongoRepository<Gift, String> {
    fun findByGiftToken(giftToken: String): Mono<Gift>
    fun findByOrderToken(orderToken: String): Mono<Gift>
}