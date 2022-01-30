package msa.order.domain.order

import reactor.core.publisher.Mono

interface OrderReader {
    fun getOrder(orderToken: String): Mono<Order>
}