package msa.order.domain.order.payment.validator

import msa.order.domain.order.Order
import msa.order.domain.order.OrderCommand


interface PaymentValidator {
    fun validate(order: Order, command: OrderCommand.PaymentRequest)
}