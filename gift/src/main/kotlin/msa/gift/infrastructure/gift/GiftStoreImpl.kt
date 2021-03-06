package msa.gift.infrastructure.gift

import kotlinx.coroutines.reactor.awaitSingle
import msa.gift.common.exception.InvalidParamException
import msa.gift.domain.gift.Gift
import msa.gift.domain.gift.GiftStore
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GiftStoreImpl(
    val giftRepository: GiftRepository
) : GiftStore {
    override suspend fun store(gift: Gift): Gift {
        if (gift == null) throw InvalidParamException()
        return giftRepository.save(gift).awaitSingle()
    }

    override fun storeMono(gift: Gift): Mono<Gift> {
        if (gift == null) throw InvalidParamException()
        return giftRepository.save(gift)
    }

}