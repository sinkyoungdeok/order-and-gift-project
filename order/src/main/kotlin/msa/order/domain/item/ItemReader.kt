package msa.order.domain.item

interface ItemReader {
    suspend fun getItemBy(itemToken: String): Item
}