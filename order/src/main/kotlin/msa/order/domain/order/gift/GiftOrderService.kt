package msa.order.domain.order.gift

import msa.order.domain.order.OrderCommand

interface GiftOrderService {
    fun paymentOrder(command: OrderCommand.PaymentRequest)
}