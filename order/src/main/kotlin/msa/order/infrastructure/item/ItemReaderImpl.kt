package msa.order.infrastructure.item

import kotlinx.coroutines.reactor.awaitSingleOrNull
import msa.order.common.exception.EntityNotFoundException
import msa.order.domain.item.Item
import msa.order.domain.item.ItemReader
import org.springframework.stereotype.Component

@Component
class ItemReaderImpl(
    val itemRepository: ItemRepository
) : ItemReader {
    override suspend fun getItemBy(itemToken: String): Item {
        return itemRepository.findByItemToken(itemToken).awaitSingleOrNull()
            ?: throw EntityNotFoundException()
    }
}