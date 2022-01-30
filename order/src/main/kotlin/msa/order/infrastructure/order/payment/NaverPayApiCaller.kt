package msa.order.infrastructure.order.payment

import msa.order.domain.order.OrderCommand
import msa.order.domain.order.payment.PayMethod
import org.springframework.stereotype.Component

@Component
class NaverPayApiCaller : PaymentApiCaller {
    override fun support(payMethod: PayMethod): Boolean {
        return PayMethod.NAVER_PAY == payMethod
    }

    override fun pay(command: OrderCommand.PaymentRequest) {
        TODO("Not yet implemented")
    }

}