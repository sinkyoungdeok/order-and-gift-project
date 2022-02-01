package msa.gift.application.gift

import msa.gift.domain.gift.GiftCommand
import msa.gift.domain.gift.GiftInfo
import msa.gift.domain.gift.GiftService
import org.springframework.stereotype.Service

@Service
class GiftFacade(
    val giftService: GiftService
) {
    suspend fun registerOrder(
        command: GiftCommand.RegisterOrder
    ): GiftInfo.Main {
        var giftInfo = giftService.registerOrder(command)
        return giftInfo
    }

    suspend fun requestPaymentProcessing(giftToken: String) {
        giftService.requestPaymentProcessing(giftToken)
    }

    fun completePayment(orderToken: String) {
        return giftService.completePayment(orderToken)
    }

    suspend fun acceptGift(command: GiftCommand.AcceptGift) {
        giftService.acceptGift(command)
    }

    suspend fun getOrder(giftToken: String): GiftInfo.Main {
        return GiftInfo.Main()
    }
}