package msa.order.infrastructure.order.payment

import msa.order.domain.order.payment.PaymentProcessor
import org.springframework.stereotype.Component

@Component
class PaymentProcessorImpl : PaymentProcessor {
    override fun pay() {
        TODO("Not yet implemented")
    }
}