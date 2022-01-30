package msa.order.infrastructure.order.payment

import msa.order.domain.order.Order
import msa.order.domain.order.OrderCommand
import msa.order.domain.order.payment.PaymentProcessor
import org.springframework.stereotype.Component

@Component
class PaymentProcessorImpl(
    val paymentApiCallerList: List<PaymentApiCaller>
) : PaymentProcessor {
    override fun pay(order: Order, command: OrderCommand.PaymentRequest) {
        TODO("Not yet implemented")
    }
}