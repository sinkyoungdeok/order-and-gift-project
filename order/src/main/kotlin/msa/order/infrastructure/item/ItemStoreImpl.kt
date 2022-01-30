package msa.order.infrastructure.item

import kotlinx.coroutines.reactor.awaitSingle
import msa.order.domain.item.Item
import msa.order.domain.item.ItemStore
import org.springframework.stereotype.Component

@Component
class ItemStoreImpl(
    val itemRepository: ItemRepository
) : ItemStore {
    override suspend fun store(initItem: Item): Item {
        return itemRepository.save(initItem).awaitSingle()
    }
}