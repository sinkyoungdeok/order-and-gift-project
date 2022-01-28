package msa.order.domain.item

import msa.order.common.util.TokenGenerator
import msa.order.domain.item.option.ItemOption
import msa.order.domain.item.optiongroup.ItemOptionGroup

class ItemCommand {

    data class RegisterItemRequest(
        var itemName: String,
        var itemPrice: Long,
        var itemOptionGroupRequestList: List<RegisterItemOptionGroupRequest>
    ) {
        private val ITEM_PREFIX = "itm_"

        fun toEntity(partnerId: String): Item {
            return Item(
                partnerId = partnerId,
                itemName = itemName,
                itemPrice = itemPrice,
                itemToken = TokenGenerator.randomCharacterWithPrefix(ITEM_PREFIX)?: "",
                itemOptionGroupList = itemOptionGroupRequestList.map { it.toEntity() }
            )
        }
    }

    data class RegisterItemOptionGroupRequest(
        var ordering: Int,
        var itemOptionGroupName: String,
        var itemOptionRequestList: List<RegisterItemOptionRequest>
    ) {
        fun toEntity(): ItemOptionGroup {
            return ItemOptionGroup(
                ordering = ordering,
                itemOptionGroupName = itemOptionGroupName,
                itemOptionList = itemOptionRequestList.map { it.toEntity() }
            )
        }
    }

    data class RegisterItemOptionRequest(
        var ordering: Int,
        var itemOptionName: String,
        var itemOptionPrice: Long
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