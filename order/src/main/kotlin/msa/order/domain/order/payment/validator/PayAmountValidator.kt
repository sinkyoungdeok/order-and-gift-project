package msa.order.domain.order.payment.validator

import msa.order.common.exception.InvalidParamException
import msa.order.domain.order.OrderCommand
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Order(value = 1)
@Component
class PayAmountValidator : PaymentValidator {
    override fun validate(
        order: msa.order.domain.order.Order,
        command: OrderCommand.PaymentRequest
    ) {
        if (!order.calculateTotalAmount()
                .equals(command.amount)
        ) throw InvalidParamException("주문가격이 불일치합니다")
    }
}