package msa.gift.domain.gift

interface GiftService {
    suspend fun registerOrder(
        command: GiftCommand.RegisterOrder
    ): GiftInfo.Main
}