package msa.order.domain.order.gift

import msa.order.domain.order.OrderCommand

interface GiftOrderService {
    suspend fun paymentOrder(command: OrderCommand.PaymentRequest)
}