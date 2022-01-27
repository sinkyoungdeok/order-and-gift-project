package msa.order.domain.item

import reactor.core.publisher.Mono

interface ItemService {
    fun registerItem(
        command: ItemCommand.RegisterItemRequest,
        partnerToken: String
    ): Mono<ItemInfo.Token>
}