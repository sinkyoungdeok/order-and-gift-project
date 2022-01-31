package msa.gift.domain.gift.order

interface OrderApiCaller {
    fun registerGiftOrder(command: OrderApiCommand.RegisterOrder): String
}