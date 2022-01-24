package msa.order.domain.item

import msa.order.domain.partner.PartnerReader
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ItemServiceImpl(
    val partnerReader: PartnerReader,
    val itemStore: ItemStore
) : ItemService {
    override fun registerItem(
        request: Mono<ItemCommand.RegisterItemRequest>,
        partnerToken: Mono<String>
    ): Mono<ItemInfo.Token> {
        var partner = partnerReader.getPartner(partnerToken)
        return partner.map { ItemInfo.Token(it.partnerToken) }
    }
}