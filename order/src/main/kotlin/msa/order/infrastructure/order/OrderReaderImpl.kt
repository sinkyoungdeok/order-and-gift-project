package msa.order.infrastructure.order


import kotlinx.coroutines.reactor.awaitSingleOrNull
import msa.order.common.exception.EntityNotFoundException
import msa.order.domain.order.Order
import msa.order.domain.order.OrderReader
import org.springframework.stereotype.Component

@Component
class OrderReaderImpl(
    val orderRepository: OrderRepository
) : OrderReader {
    override suspend fun getOrder(orderToken: String): Order {
        return orderRepository.findByOrderToken(orderToken).awaitSingleOrNull()
            ?: throw EntityNotFoundException()
    }
}