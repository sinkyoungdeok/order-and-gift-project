package msa.order.domain.order.payment.validator

import msa.order.common.exception.InvalidParamException
import msa.order.domain.order.OrderCommand
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Order(value = 3)
@Component
class PayStatusValidator : PaymentValidator {
    override fun validate(
        order: msa.order.domain.order.Order,
        command: OrderCommand.PaymentRequest
    ) {
        if (order.isAlreadyPaymentComplete()) {
            throw InvalidParamException("이미 결제완료된 주문입니다.")
        }
    }
}