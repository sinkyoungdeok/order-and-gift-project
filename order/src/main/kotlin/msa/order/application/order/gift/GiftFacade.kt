package msa.order.application.order.gift

import msa.order.domain.order.OrderCommand
import msa.order.domain.order.gift.GiftOrderService
import org.springframework.stereotype.Service

@Service
class GiftFacade(
    val giftOrderService: GiftOrderService
) {
    suspend fun paymentOrder(command: OrderCommand.PaymentRequest) {
        giftOrderService.paymentOrder(command)
    }

    suspend fun updateReceiverInfo(
        orderToken: String,
        command: OrderCommand.UpdateReceiverInfoRequest
    ) {

    }
}