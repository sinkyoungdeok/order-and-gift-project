package msa.gift.domain.gift

interface GiftStore {
    fun store(gift: Gift): Gift
}