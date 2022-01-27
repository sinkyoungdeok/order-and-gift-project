package msa.order.domain.item

import msa.order.common.util.TokenGenerator

class ItemCommand {

    class RegisterItemRequest(
        var itemName: String? = null,
        var itemPrice: Long? = null,
        var itemOptionGroupRequestList: List<RegisterItemOptionGroupRequest>? = null
    ) {
        private val ITEM_PREFIX = "itm_"

        fun toEntity(partnerId: String): Item {
            return Item(
                partnerId = partnerId,
                itemName = itemName,
                itemPrice = itemPrice,
                itemToken = TokenGenerator.randomCharacterWithPrefix(ITEM_PREFIX)
            )
        }
    }

    data class RegisterItemOptionGroupRequest(
        var ordering: Int? = null,
        var itemOptionGroupName: String? = null,
        var itemOptionRequestList: List<RegisterItemOptionRequest>? = null,
    )

    data class RegisterItemOptionRequest(
        var ordering: Int? = null,
        var itemOptionName: String? = null,
        var itemOptionPrice: Long? = null
    )
}