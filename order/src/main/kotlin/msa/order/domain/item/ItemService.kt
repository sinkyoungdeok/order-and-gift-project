package msa.order.domain.item

interface ItemService {
    suspend fun registerItem(
        command: ItemCommand.RegisterItemRequest,
        partnerToken: String
    ): ItemInfo.Token

    suspend fun changeOnSale(
        itemToken: String
    ): ItemInfo.Token

    suspend fun changeEndOfSale(
        itemToken: String
    ): ItemInfo.Token

    suspend fun retrieveItemInfo(
        itemToken: String
    ): ItemInfo.Main
}