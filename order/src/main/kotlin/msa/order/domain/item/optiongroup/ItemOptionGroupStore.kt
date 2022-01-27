package msa.order.domain.item.optiongroup

interface ItemOptionGroupStore {
    fun store(itemOptionGroup: ItemOptionGroup): ItemOptionGroup
}