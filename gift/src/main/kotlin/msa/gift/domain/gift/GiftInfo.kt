package msa.gift.domain.gift

class GiftInfo {
    class Main(
        var orderToken: String,
        var giftToken: String,
        var pushType: Gift.PushType? = null,
        var giftReceiverName: String,
        var giftReceiverPhone: String,
        var giftMessage: String
    ) {
        constructor(): this(
            "",
            "",
            null,
            "",
            "",
            ""
        )
    }
}