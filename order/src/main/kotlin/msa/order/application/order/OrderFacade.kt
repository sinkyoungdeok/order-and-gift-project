package msa.order.application.order

import msa.order.domain.order.OrderCommand
import msa.order.domain.order.OrderInfo
import msa.order.domain.order.OrderService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderFacade(val orderService: OrderService) {

    fun registerOrder(registerOrder: OrderCommand.RegisterOrder): Mono<OrderInfo.Token> {
        val orderInfo = orderService.registerOrder(registerOrder)

        return orderInfo
    }
}