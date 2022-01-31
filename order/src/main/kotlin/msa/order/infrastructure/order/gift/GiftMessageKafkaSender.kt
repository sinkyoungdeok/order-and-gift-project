package msa.order.infrastructure.order.gift

import msa.order.domain.order.gift.GiftMessageChannelSender
import msa.order.domain.order.gift.GiftPaymentCompleteMessage

class GiftMessageKafkaSender : GiftMessageChannelSender {
    override fun paymentComplete(message: GiftPaymentCompleteMessage) {
        TODO("Not yet implemented")
    }
}