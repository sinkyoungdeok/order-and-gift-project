package msa.order.domain.order.gift

import msa.order.common.exception.IllegalStatusException
import msa.order.domain.order.Order
import msa.order.domain.order.OrderCommand
import msa.order.domain.order.OrderReader
import msa.order.domain.order.OrderStore
import msa.order.domain.order.payment.PaymentProcessor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GiftOrderServiceImpl(
    val orderReader: OrderReader,
    val paymentProcessor: PaymentProcessor,
    val giftMessageChannelSender: GiftMessageChannelSender,
    val orderStore: OrderStore
) : GiftOrderService {
    @Transactional
    override suspend fun paymentOrder(command: OrderCommand.PaymentRequest) {
        var order = orderReader.getOrder(command.orderToken)

        if (order.status != Order.Status.INIT) throw IllegalStatusException()

        paymentProcessor.pay(order, command)
        order.orderComplete()
        orderStore.store(order)
        giftMessageChannelSender
            .paymentComplete(GiftPaymentCompleteMessage(order.orderToken))
    }
}