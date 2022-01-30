package msa.order.infrastructure.order

import msa.order.domain.order.Order
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface OrderRepository: ReactiveMongoRepository<Order, String> {
    fun findByOrderToken(orderToken: String): Mono<Order>
}