package msa.order.domain.order

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class OrderServiceImpl(
    val orderItemSeriesFactory: OrderItemSeriesFactory
) : OrderService {

    @Transactional
    override suspend fun registerOrder(orderCommand: OrderCommand.RegisterOrder): OrderInfo.Token {
        val initOrder = orderCommand.toEntity()
        val order: Order = orderItemSeriesFactory.store(initOrder, orderCommand)

        return OrderInfo.Token(order.orderToken)
    }

    @Transactional
    override fun paymentOrder(command: OrderCommand.PaymentRequest): Mono<OrderInfo.Token> {
        TODO("Not yet implemented")
    }

}