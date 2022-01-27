package msa.order.domain.item.option

interface ItemOptionStore {
    fun store(itemOption: ItemOption): Void
}