package msa.gift.domain.gift

import msa.gift.common.util.TokenGenerator


class GiftCommand {
    class RegisterOrder(
        var buyerUserId: String,
        var payMethod: String,
        var pushType: String,
        var giftReceiverName: String,
        var giftReceiverPhone: String,
        var giftMessage: String,
        var orderItemList: List<RegisterOrderItem>
    ) {
        private val GIFT_PREFIX = "gt_"

        fun toEntity(orderToken: String): Gift {
            return Gift(
                buyerUserId = buyerUserId,
                orderToken = orderToken,
                pushType = Gift.PushType.valueOf(pushType),
                giftReceiverName = giftReceiverName,
                giftReceiverPhone = giftReceiverPhone,
                giftMessage = giftMessage,
                giftToken = TokenGenerator.randomCharacterWithPrefix(GIFT_PREFIX) ?: "",
                status = Gift.Status.INIT
            )
        }
    }

    class RegisterOrderItem(
        var orderCount: Int,
        var itemToken: String,
        var itemName: String,
        var itemPrice: Long,
        var orderItemOptionGroupList: List<RegisterOrderItemOptionGroup>
    )

    class RegisterOrderItemOptionGroup(
        var ordering: Int,
        var itemOptionGroupName: String,
        var orderItemOptionList: List<RegisterOrderItemOption>
    )

    class RegisterOrderItemOption(
        var ordering: Int,
        var itemOptionName: String,
        var itemOptionPrice: Long
    )
}