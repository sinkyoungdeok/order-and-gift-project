package msa.gift.domain.gift

import reactor.core.publisher.Mono

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
}