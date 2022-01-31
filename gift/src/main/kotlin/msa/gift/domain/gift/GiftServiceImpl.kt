package msa.gift.domain.gift

import org.springframework.stereotype.Service

@Service
class GiftServiceImpl : GiftService {
    override fun registerOrder(
        command: GiftCommand.RegisterOrder
    ): GiftInfo.Main {
        return GiftInfo.Main("11", "12", null, "", "", "")
    }
}