package msa.order.domain.item

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ItemServiceImpl : ItemService {
    override fun registerItem(
        request: Mono<ItemCommand.RegisterItemRequest>,
        partnerToken: Mono<String>
    ): Mono<ItemInfo.Token> {
        return Mono.just(ItemInfo.Token("test"))
    }
}