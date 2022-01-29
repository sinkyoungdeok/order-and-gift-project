package msa.order.domain.order

import reactor.core.publisher.Mono

interface OrderService {
    fun registerOrder(
        registerOrder: OrderCommand.RegisterOrder
    ): Mono<OrderInfo.Token>
}