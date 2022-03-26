package msa.order.domain.order


interface OrderService {
    suspend fun registerOrder(
        command: OrderCommand.RegisterOrder, userName: String
    ): OrderInfo.Token

    suspend fun paymentOrder(
        command: OrderCommand.PaymentRequest
    ): OrderInfo.Token

    suspend fun retrieveOrder(
        orderToken: String
    ): OrderInfo.Main

    suspend fun updateReceiverInfo(
        orderToken: String,
        command: OrderCommand.UpdateReceiverInfoRequest
    )
}