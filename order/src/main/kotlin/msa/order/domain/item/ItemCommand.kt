package msa.order.domain.item

import msa.order.common.util.TokenGenerator
import msa.order.domain.item.option.ItemOption
import msa.order.domain.item.optiongroup.ItemOptionGroup

class ItemCommand {

    class RegisterItemRequest(
        val itemName: String,
        val itemPrice: Long,
        val itemOptionGroupRequestList: List<RegisterItemOptionGroupRequest>
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

    class RegisterItemOptionGroupRequest(
        val ordering: Int,
        val itemOptionGroupName: String,
        val itemOptionRequestList: List<RegisterItemOptionRequest>
    ) {
        fun toEntity(): ItemOptionGroup {
            return ItemOptionGroup(
                ordering = ordering,
                itemOptionGroupName = itemOptionGroupName,
                itemOptionList = itemOptionRequestList.map { it.toEntity() }
            )
        }
    }

    class RegisterItemOptionRequest(
        val ordering: Int,
        val itemOptionName: String,
        val itemOptionPrice: Long
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