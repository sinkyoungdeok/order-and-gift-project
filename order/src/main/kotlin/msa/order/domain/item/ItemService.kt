package msa.order.domain.item

import reactor.core.publisher.Mono

interface ItemService {
    fun registerItem(
        command: Mono<ItemCommand.RegisterItemRequest>,
        partnerToken: Mono<String>
    ): Mono<ItemInfo.Token>
}