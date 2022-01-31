package msa.gift.domain.gift

import reactor.core.publisher.Mono

interface GiftReader {
    suspend fun getGiftBy(giftToken: String): Gift
    fun getGiftByOrderToken(orderToken: String): Mono<Gift>
}