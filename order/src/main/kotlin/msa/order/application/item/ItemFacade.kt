package msa.order.application.item

import msa.order.domain.item.ItemCommand
import msa.order.domain.item.ItemInfo
import msa.order.domain.item.ItemService
import org.springframework.stereotype.Service

@Service
class ItemFacade(val itemService: ItemService) {

    suspend fun registerItem(
        request: ItemCommand.RegisterItemRequest,
        partnerToken: String
    ): ItemInfo.Token {
        var itemInfo = itemService.registerItem(request, partnerToken)
        return itemInfo
    }

    suspend fun changeOnSaleItem(itemToken: String): ItemInfo.Token {
        return itemService.changeOnSale(itemToken)
    }

    suspend fun changeEndOfSaleItem(itemToken: String): ItemInfo.Token {
        return itemService.changeEndOfSale(itemToken)
    }

    suspend fun retrieveItemInfo(itemToken: String): ItemInfo.Main {
        return itemService.retrieveItemInfo(itemToken)
    }
}