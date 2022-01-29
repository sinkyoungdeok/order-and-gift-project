package msa.order.infrastructure.order

import msa.order.domain.order.Order
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface OrderRepository: ReactiveMongoRepository<Order, String> {
}