package msa.order.infrastructure.order

import msa.order.domain.order.Order
import msa.order.domain.order.OrderReader
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class OrderReaderImpl : OrderReader {
    override fun getOrder(orderToken: String): Mono<Order> {
        TODO("Not yet implemented")
    }
}