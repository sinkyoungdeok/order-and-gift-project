package msa.order.domain.order.gift

import org.springframework.stereotype.Component

@Component
interface GiftMessageChannelSender {
    fun paymentComplete(message: GiftPaymentCompleteMessage)
}