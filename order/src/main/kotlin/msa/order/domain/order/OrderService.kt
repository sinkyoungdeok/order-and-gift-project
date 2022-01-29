package msa.order.domain.order

interface OrderService {
    suspend fun registerOrder(
        registerOrder: OrderCommand.RegisterOrder
    ): OrderInfo.Token
}