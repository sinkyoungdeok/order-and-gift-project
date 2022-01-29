package msa.order.application.order

import msa.order.domain.order.OrderCommand
import msa.order.domain.order.OrderInfo
import msa.order.domain.order.OrderService
import org.springframework.stereotype.Service

@Service
class OrderFacade(val orderService: OrderService) {

    suspend fun registerOrder(registerOrder: OrderCommand.RegisterOrder): OrderInfo.Token {
        val orderInfo = orderService.registerOrder(registerOrder)

        return orderInfo
    }
}