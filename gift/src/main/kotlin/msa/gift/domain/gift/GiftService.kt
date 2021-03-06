package msa.gift.domain.gift

interface GiftService {
    suspend fun registerOrder(
        command: GiftCommand.RegisterOrder
    ): GiftInfo.Main

    suspend fun requestPaymentProcessing(
        giftToken: String
    )

    fun completePayment(
        orderToken: String
    )

    suspend fun acceptGift(
        command: GiftCommand.AcceptGift
    )

    suspend fun getGiftInfo(
        giftToken: String
    ): GiftInfo.Main
}