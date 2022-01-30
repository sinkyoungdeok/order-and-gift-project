package msa.order.infrastructure.order.payment

import msa.order.common.exception.InvalidParamException
import msa.order.domain.order.Order
import msa.order.domain.order.OrderCommand
import msa.order.domain.order.payment.PaymentProcessor
import msa.order.domain.order.payment.validator.PaymentValidator
import org.springframework.stereotype.Component
import java.util.function.Supplier

@Component
class PaymentProcessorImpl(
    val paymentValidatorList: List<PaymentValidator>,
    val paymentApiCallerList: List<PaymentApiCaller>
) : PaymentProcessor {
    override fun pay(order: Order, command: OrderCommand.PaymentRequest) {
        paymentValidatorList.forEach { it.validate(order, command) }
        val payApiCaller = routingApiCaller(command)
        payApiCaller.pay(command)
    }

    fun routingApiCaller(command: OrderCommand.PaymentRequest): PaymentApiCaller {
        return paymentApiCallerList.stream()
            .filter { it.support(command.payMethod) }
            .findFirst()
            .orElseThrow(Supplier<RuntimeException> { InvalidParamException() })
    }
}