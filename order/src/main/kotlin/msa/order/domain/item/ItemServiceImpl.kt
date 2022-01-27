package msa.order.domain.item

import msa.order.domain.partner.PartnerReader
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ItemServiceImpl(
    val partnerReader: PartnerReader,
    val itemStore: ItemStore,
) : ItemService {

    override fun registerItem(
        command: ItemCommand.RegisterItemRequest,
        partnerToken: String
    ): Mono<ItemInfo.Token> {
        var partner = partnerReader.getPartner(partnerToken)
        var initItem = partner.map { it.id?.let { it1 -> command.toEntity(it1) } }
        var item = initItem.flatMap { itemStore.store(it) }
        return item.map { ItemInfo.Token(it.itemToken) }
    }
}
