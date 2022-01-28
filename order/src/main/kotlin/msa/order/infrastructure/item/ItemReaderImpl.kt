package msa.order.infrastructure.item

import msa.order.domain.item.Item
import msa.order.domain.item.ItemReader
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class ItemReaderImpl(
    val itemRepository: ItemRepository
) : ItemReader {
    override fun getItemBy(itemToken: String): Mono<Item> {
        return itemRepository.findByItemToken(itemToken)
    }
}