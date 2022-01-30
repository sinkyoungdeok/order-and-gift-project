package msa.order.domain.order.payment

import msa.order.domain.order.Order
import msa.order.domain.order.OrderCommand

interface PaymentProcessor {
    fun pay(order: Order, command: OrderCommand.PaymentRequest)
}