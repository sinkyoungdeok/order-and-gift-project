package msa.order.application.order

import msa.order.domain.order.OrderCommand
import msa.order.domain.order.OrderInfo
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderFacade {

    fun registerOrder(registerOrder: Mono<OrderCommand.RegisterOrder>): Mono<OrderInfo.Token> {
        return Mono.just(OrderInfo.Token("test"))
    }
}