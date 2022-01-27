package msa.order.domain

import msa.order.domain.item.Item
import msa.order.domain.item.ItemCommand
import msa.order.domain.item.optiongroup.ItemOptionGroup

interface ItemOptionSeriesFactory {
    fun store(request: ItemCommand.RegisterItemRequest, item: Item): List<ItemOptionGroup>
}