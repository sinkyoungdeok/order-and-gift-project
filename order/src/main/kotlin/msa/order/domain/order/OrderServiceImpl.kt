package msa.order.domain.order

import msa.order.domain.order.payment.PaymentProcessor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderServiceImpl(
    val orderItemSeriesFactory: OrderItemSeriesFactory,
    val orderReader: OrderReader,
    val paymentProcessor: PaymentProcessor,
    val orderStore: OrderStore,
    val orderInfoMapper: OrderInfoMapper
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

    @Transactional(readOnly = true)
    override suspend fun retrieveOrder(orderToken: String): OrderInfo.Main {
        var order: Order = orderReader.getOrder(orderToken)
        var orderItemList = order.orderItemList
        return orderInfoMapper.of(order, orderItemList)
    }

    @Transactional
    override suspend fun updateReceiverInfo(
        orderToken: String,
        command: OrderCommand.UpdateReceiverInfoRequest
    ) {
        var order = orderReader.getOrder(orderToken)
        order.updateDeliveryFragment(command)
        order.deliveryPrepare()
        orderStore.store(order)
    }

}