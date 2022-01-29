package msa.order.domain.order

interface OrderItemSeriesFactory {
    suspend fun store(
        initOrder: Order,
        orderCommand: OrderCommand.RegisterOrder
    ): Order
}