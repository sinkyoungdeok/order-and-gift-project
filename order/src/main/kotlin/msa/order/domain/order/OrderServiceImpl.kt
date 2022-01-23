package msa.order.domain.order

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class OrderServiceImpl : OrderService {

    @Transactional
    override fun registerOrder(registerOrder: Mono<OrderCommand.RegisterOrder>): Mono<OrderInfo.Token> {
        return Mono.just(OrderInfo.Token("test"))
    }
}