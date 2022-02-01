package msa.order.application.order

import msa.order.application.order.event.OrderKakaoEvent
import msa.order.domain.order.OrderCommand
import msa.order.domain.order.OrderInfo
import msa.order.domain.order.OrderService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class OrderFacade(
    val orderService: OrderService,
    val eventPublisher: ApplicationEventPublisher
) {

    suspend fun registerOrder(command: OrderCommand.RegisterOrder): OrderInfo.Token {
        val orderInfo = orderService.registerOrder(command)
        eventPublisher.publishEvent(OrderKakaoEvent())
        return orderInfo
    }

    suspend fun paymentOrder(command: OrderCommand.PaymentRequest): OrderInfo.Token {
        val orderInfo = orderService.paymentOrder(command)
        eventPublisher.publishEvent(OrderKakaoEvent())
        return orderInfo
    }

    suspend fun retrieveOrder(orderToken: String): OrderInfo.Main {
        return orderService.retrieveOrder(orderToken)
    }

    suspend fun updateReceiverInfo(
        orderToken: String,
        command: OrderCommand.UpdateReceiverInfoRequest
    ) {
        orderService.updateReceiverInfo(orderToken, command)
        eventPublisher.publishEvent(OrderKakaoEvent())
    }
}