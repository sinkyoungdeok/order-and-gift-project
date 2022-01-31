package msa.gift.interfaces.gift.message

import msa.gift.application.gift.GiftFacade
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class GiftKafkaMessageListener(
    val giftFacade: GiftFacade
) {

    @KafkaListener(topics = arrayOf("pay-complete"))
    fun payComplete(message: String) {
        println("receive" + message)
    }
}