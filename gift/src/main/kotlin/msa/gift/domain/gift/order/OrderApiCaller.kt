package msa.gift.domain.gift.order

import msa.gift.domain.gift.GiftCommand

interface OrderApiCaller {
    fun registerGiftOrder(command: OrderApiCommand.RegisterOrder): String

    fun updateReceiverInfo(orderToken: String, command: GiftCommand.AcceptGift)

    fun login(command: OrderApiCommand.LoginRequest): String
}