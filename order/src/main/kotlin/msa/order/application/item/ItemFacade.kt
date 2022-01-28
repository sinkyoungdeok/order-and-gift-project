package msa.order.application.item

import msa.order.domain.item.ItemCommand
import msa.order.domain.item.ItemInfo
import msa.order.domain.item.ItemService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ItemFacade(val itemService: ItemService) {

    fun registerItem(
        request: ItemCommand.RegisterItemRequest,
        partnerToken: String
    ): Mono<ItemInfo.Token> {
        var itemInfo = itemService.registerItem(request, partnerToken)
        return itemInfo
    }

    fun changeOnSaleItem(itemToken: String): Mono<ItemInfo.Token> {
        return itemService.changeOnSale(itemToken)
    }
}