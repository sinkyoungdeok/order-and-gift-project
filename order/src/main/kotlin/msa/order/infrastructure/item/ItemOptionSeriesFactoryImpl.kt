package msa.order.infrastructure.item

import msa.order.domain.ItemOptionSeriesFactory
import msa.order.domain.item.Item
import msa.order.domain.item.ItemCommand
import msa.order.domain.item.optiongroup.ItemOptionGroup
import org.springframework.stereotype.Component

@Component
class ItemOptionSeriesFactoryImpl : ItemOptionSeriesFactory {
    override fun store(
        request: ItemCommand.RegisterItemRequest,
        item: Item
    ): List<ItemOptionGroup> {
        TODO("Not yet implemented")
    }
}