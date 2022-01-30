package msa.order.domain.order

import msa.order.domain.order.payment.PaymentProcessor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(
    val orderItemSeriesFactory: OrderItemSeriesFactory,
    val orderReader: OrderReader,
    val paymentProcessor: PaymentProcessor,
    val orderStore: OrderStore
) : OrderService {

    @Transactional
    override suspend fun registerOrder(orderCommand: OrderCommand.RegisterOrder): OrderInfo.Token {
        val initOrder = orderCommand.toEntity()
        val order: Order = orderItemSeriesFactory.store(initOrder, orderCommand)

        return OrderInfo.Token(order.orderToken)
    }

    @Transactional
    override suspend fun paymentOrder(command: OrderCommand.PaymentRequest): OrderInfo.Token {
        val orderToken: String = command.orderToken
        val order: Order = orderReader.getOrder(orderToken)
        paymentProcessor.pay(order, command)
        order.orderComplete()
        orderStore.store(order)
        return OrderInfo.Token(orderToken)
    }

    override suspend fun retrieveOrder(orderToken: String): OrderInfo.Main {
        TODO("Not yet implemented")
    }

}