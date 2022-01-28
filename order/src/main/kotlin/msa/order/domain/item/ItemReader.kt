package msa.order.domain.item

import reactor.core.publisher.Mono

interface ItemReader {
    fun getItemBy(itemToken: String): Mono<Item>
}