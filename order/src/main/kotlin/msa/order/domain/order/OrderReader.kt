package msa.order.domain.order

interface OrderReader {
    suspend fun getOrder(orderToken: String): Order
}