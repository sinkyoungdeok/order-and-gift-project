package msa.gift.domain.gift

interface GiftReader {
    fun getGifyBy(giftToken: String): Gift
}