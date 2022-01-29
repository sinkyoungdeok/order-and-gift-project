package msa.order.infrastructure.order

import msa.order.domain.order.Order
import msa.order.domain.order.OrderStore
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class OrderStoreImpl(
    val orderRepository: OrderRepository
) : OrderStore {
    override fun store(order: Order): Mono<Order> {
        return orderRepository.save(order)
    }
}