package msa.gift.application.gift

import msa.gift.domain.gift.GiftCommand
import msa.gift.domain.gift.GiftInfo
import org.springframework.stereotype.Service

@Service
class GiftFacade {
    fun registerOrder(command: GiftCommand.RegisterOrder): GiftInfo.Main {
        return GiftInfo.Main()
    }
}