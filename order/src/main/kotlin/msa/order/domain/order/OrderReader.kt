package msa.order.domain.order

interface OrderReader {
    fun getOrder(orderToken: String): Order
}