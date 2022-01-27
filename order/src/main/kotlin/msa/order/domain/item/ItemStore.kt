package msa.order.domain.item

import reactor.core.publisher.Mono

interface ItemStore {
    fun store(initItem: Item): Mono<Item>
}