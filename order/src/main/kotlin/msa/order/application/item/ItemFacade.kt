package msa.order.application.item

import msa.order.domain.item.ItemCommand
import msa.order.domain.item.ItemInfo
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ItemFacade {

    fun registerItem(
        request: Mono<ItemCommand.RegisterItemRequest>,
        partnerToken: Mono<String>
    ): Mono<ItemInfo.Token> {
        return Mono.just(ItemInfo.Token("test"))
    }
}