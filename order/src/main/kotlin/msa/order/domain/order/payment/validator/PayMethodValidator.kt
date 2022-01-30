package msa.order.domain.order.payment.validator

import msa.order.common.exception.InvalidParamException
import msa.order.domain.order.OrderCommand
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Order(value = 2)
@Component
class PayMethodValidator : PaymentValidator {
    override fun validate(
        order: msa.order.domain.order.Order,
        command: OrderCommand.PaymentRequest
    ) {
        if (!order.payMethod.equals(command.payMethod.name)) {
            throw InvalidParamException("주문 과정에서의 결제수단이 다릅니다.")
        }
    }
}