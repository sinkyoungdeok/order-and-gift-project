package msa.order.domain.order


interface OrderStore {
    suspend fun store(order: Order): Order
}