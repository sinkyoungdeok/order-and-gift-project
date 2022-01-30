package msa.order.infrastructure.order.payment

import msa.order.domain.order.OrderCommand
import msa.order.domain.order.payment.PayMethod

interface PaymentApiCaller {
    fun support(payMethod: PayMethod): Boolean

    fun pay(command: OrderCommand.PaymentRequest)
}