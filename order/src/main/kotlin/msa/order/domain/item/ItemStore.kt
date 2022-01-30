package msa.order.domain.item

interface ItemStore {
    suspend fun store(initItem: Item): Item
}