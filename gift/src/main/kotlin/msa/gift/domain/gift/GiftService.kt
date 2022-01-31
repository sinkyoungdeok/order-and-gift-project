package msa.gift.domain.gift

interface GiftService {
    fun registerOrder(
        command: GiftCommand.RegisterOrder
    ): GiftInfo.Main
}