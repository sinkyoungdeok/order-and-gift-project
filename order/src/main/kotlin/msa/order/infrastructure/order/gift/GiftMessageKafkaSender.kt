package msa.order.infrastructure.order.gift

import msa.order.domain.order.gift.GiftMessageChannelSender
import msa.order.domain.order.gift.GiftPaymentCompleteMessage
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class GiftMessageKafkaSender(
    val kafkaTemplate: KafkaTemplate<String, String>
) : GiftMessageChannelSender {
    val topicName: String = "pay-complete"

    override fun paymentComplete(message: GiftPaymentCompleteMessage) {
        kafkaTemplate.send(topicName, message.orderToken)
    }
}