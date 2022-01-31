package msa.gift.domain.gift

interface GiftStore {
    suspend fun store(gift: Gift): Gift
}