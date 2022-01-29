package msa.order.domain.order

import msa.order.common.util.TokenGenerator
import msa.order.domain.item.Item
import msa.order.domain.order.fragment.DeliveryFragment
import msa.order.domain.order.item.OrderItem
import msa.order.domain.order.item.OrderItemOption
import msa.order.domain.order.item.OrderItemOptionGroup


class OrderCommand {

    data class RegisterOrder(
        var userId: String,
        var payMethod: String,
        var receiverName: String,
        var receiverPhone: String,
        var receiverZipcode: String,
        var receiverAddress1: String,
        var receiverAddress2: String,
        var etcMessage: String,
        var orderItemList: List<RegisterOrderItem>
    ) {
        private val ORDER_PREFIX = "ord_"

        constructor() : this(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            arrayListOf()
        )

        fun toEntity(): Order {
            var deliveryFragment = DeliveryFragment(
                receiverName = receiverName,
                receiverPhone = receiverPhone,
                receiverZipcode = receiverZipcode,
                receiverAddress1 = receiverAddress1,
                receiverAddress2 = receiverAddress2,
                etcMessage = etcMessage
            )

            return Order(
                orderToken = TokenGenerator.randomCharacterWithPrefix(ORDER_PREFIX) ?: "",
                userId = userId,
                payMethod = payMethod,
                deliveryFragment = deliveryFragment
            )
        }


    }

    data class RegisterOrderItem(
        var orderCount: Int,
        var itemToken: String,
        var itemName: String,
        var itemPrice: Long,
        var orderItemOptionGroupList: List<RegisterOrderItemOptionGroup>
    ) {
        constructor() : this(
            0,
            "",
            "",
            0,
            arrayListOf()
        )

        fun toEntity(item: Item): OrderItem {
            return OrderItem(
                orderCount = orderCount,
                partnerId = item.partnerId,
                itemId = item.id ?: "",
                itemToken = itemToken,
                itemName = itemName,
                itemPrice = itemPrice
            )
        }
    }

    data class RegisterOrderItemOptionGroup(
        var ordering: Int,
        var itemOptionGroupName: String,
        var orderItemOptionList: List<RegisterOrderItemOption>
    ) {
        constructor() : this(
            0,
            "",
            arrayListOf()
        )

        fun toEntity(): OrderItemOptionGroup {
            return OrderItemOptionGroup(
                ordering = ordering,
                itemOptionGroupName = itemOptionGroupName
            )
        }
    }

    data class RegisterOrderItemOption(
        var ordering: Int,
        var itemOptionName: String,
        var itemOptionPrice: Long
    ) {
        constructor() : this(
            0,
            "",
            0
        )

        fun toEntity(): OrderItemOption {
            return OrderItemOption(
                ordering = ordering,
                itemOptionName = itemOptionName,
                itemOptionPrice = itemOptionPrice
            )
        }
    }

}