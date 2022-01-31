package msa.gift.domain.gift

interface GiftService {
    suspend fun registerOrder(
        command: GiftCommand.RegisterOrder
    ): GiftInfo.Main

    suspend fun requestPaymentProcessing(
        giftToken: String
    )
}