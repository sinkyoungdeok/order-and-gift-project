package msa.gift.infrastructure.gift

import msa.gift.domain.gift.Gift
import msa.gift.domain.gift.GiftStore
import org.springframework.stereotype.Component

@Component
class GiftStoreImpl : GiftStore {
    override fun store(gift: Gift): Gift {
        TODO("Not yet implemented")
    }
}