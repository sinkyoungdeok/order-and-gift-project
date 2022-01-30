package msa.order.domain.order

import reactor.core.publisher.Mono

interface OrderService {
    suspend fun registerOrder(
        command: OrderCommand.RegisterOrder
    ): OrderInfo.Token

    fun paymentOrder(
        command: OrderCommand.PaymentRequest
    ): Mono<OrderInfo.Token>
}