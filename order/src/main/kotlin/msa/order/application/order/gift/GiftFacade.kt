package msa.order.application.order.gift

import msa.order.domain.order.OrderCommand
import org.springframework.stereotype.Service

@Service
class GiftFacade {

    suspend fun paymentOrder(command: OrderCommand.PaymentRequest) {

    }
}