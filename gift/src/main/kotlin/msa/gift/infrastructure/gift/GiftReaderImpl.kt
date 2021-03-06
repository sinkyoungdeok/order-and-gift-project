package msa.gift.infrastructure.gift

import kotlinx.coroutines.reactor.awaitSingleOrNull
import msa.gift.common.exception.EntityNotFoundException
import msa.gift.common.exception.InvalidParamException
import msa.gift.domain.gift.Gift
import msa.gift.domain.gift.GiftReader
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GiftReaderImpl(
    val giftRepository: GiftRepository
) : GiftReader {
    override suspend fun getGiftBy(giftToken: String): Gift {
        if (StringUtils.isEmpty(giftToken)) throw InvalidParamException()
        return giftRepository.findByGiftToken(giftToken).awaitSingleOrNull() ?: throw EntityNotFoundException()
    }

    override fun getGiftByOrderToken(orderToken: String): Mono<Gift> {
        if (StringUtils.isEmpty(orderToken)) throw InvalidParamException()
        return giftRepository.findByOrderToken(orderToken)
    }
}