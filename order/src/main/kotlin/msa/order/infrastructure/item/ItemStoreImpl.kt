package msa.order.infrastructure.item

import msa.order.domain.item.Item
import msa.order.domain.item.ItemStore
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ItemStoreImpl: ItemStore {
    override fun store(initItem: Item): Mono<Item> {
        return Mono.just(Item(null,null,null,null,null,null,null))
    }
}