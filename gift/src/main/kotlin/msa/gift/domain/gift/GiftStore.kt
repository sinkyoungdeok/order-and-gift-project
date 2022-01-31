package msa.gift.domain.gift

import reactor.core.publisher.Mono

interface GiftStore {
    suspend fun store(gift: Gift): Gift
    fun storeMono(gift: Gift): Mono<Gift>
}