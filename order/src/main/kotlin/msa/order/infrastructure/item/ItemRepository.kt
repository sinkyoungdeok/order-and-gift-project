package msa.order.infrastructure.item

import msa.order.domain.item.Item
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ItemRepository : ReactiveMongoRepository<Item, String> {
}