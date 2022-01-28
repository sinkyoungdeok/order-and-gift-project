package msa.order.infrastructure.item

import msa.order.domain.item.Item
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface ItemRepository : ReactiveMongoRepository<Item, String> {
    fun findByItemToken(itemToken: String): Mono<Item>
}