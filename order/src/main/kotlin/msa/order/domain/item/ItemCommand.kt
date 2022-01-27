package msa.order.domain.item

import msa.order.common.util.TokenGenerator
import msa.order.domain.item.option.ItemOption
import msa.order.domain.item.optiongroup.ItemOptionGroup

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
                itemToken = TokenGenerator.randomCharacterWithPrefix(ITEM_PREFIX),
                itemOptionGroupList = itemOptionGroupRequestList?.map { it.toEntity() }
            )
        }
    }

    data class RegisterItemOptionGroupRequest(
        var ordering: Int? = null,
        var itemOptionGroupName: String? = null,
        var itemOptionRequestList: List<RegisterItemOptionRequest>? = null,
    ) {
        fun toEntity(): ItemOptionGroup {
            return ItemOptionGroup(
                ordering = ordering,
                itemOptionGroupName = itemOptionGroupName,
                itemOptionList = itemOptionRequestList?.map { it.toEntity() }
            )
        }
    }

    data class RegisterItemOptionRequest(
        var ordering: Int? = null,
        var itemOptionName: String? = null,
        var itemOptionPrice: Long? = null
    ) {
        fun toEntity(): ItemOption {
            return ItemOption(
                ordering = ordering,
                itemOptionName = itemOptionName,
                itemOptionPrice = itemOptionPrice
            )
        }
    }
}