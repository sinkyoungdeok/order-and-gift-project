package msa.order.infrastructure.order

import kotlinx.coroutines.reactive.awaitSingle
import msa.order.domain.order.Order
import msa.order.domain.order.OrderStore
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class OrderStoreImpl(
    val orderRepository: OrderRepository
) : OrderStore {
    override suspend fun store(order: Order): Order {
        return orderRepository.save(order).awaitSingle()
    }
}