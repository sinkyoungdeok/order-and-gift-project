package msa.order.application.order

import msa.order.domain.order.OrderCommand
import msa.order.domain.order.OrderInfo
import msa.order.domain.order.OrderService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderFacade(val orderService: OrderService) {

    suspend fun registerOrder(command: OrderCommand.RegisterOrder): OrderInfo.Token {
        val orderInfo = orderService.registerOrder(command)

        return orderInfo
    }

    suspend fun paymentOrder(command: OrderCommand.PaymentRequest): OrderInfo.Token {
        return orderService.paymentOrder(command)
    }
}