package msa.gift.domain.gift

interface GiftReader {
    suspend fun getGiftBy(giftToken: String): Gift
}