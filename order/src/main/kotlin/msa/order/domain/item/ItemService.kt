package msa.order.domain.item

import reactor.core.publisher.Mono

interface ItemService {
    fun registerItem(request: Mono<ItemCommand.RegisterItemRequest>, partnerToken: Mono<String>): Mono<ItemInfo.Token>
}