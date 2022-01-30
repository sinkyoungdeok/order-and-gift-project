package msa.order.infrastructure.order

import msa.order.domain.order.Order
import msa.order.domain.order.OrderReader
import org.springframework.stereotype.Component

@Component
class OrderReaderImpl : OrderReader {
    override fun getOrder(orderToken: String): Order {
        TODO("Not yet implemented")
    }
}