package msa.order.domain.order

import msa.order.interfaces.order.OrderDto

class OrderCommand {

    data class RegisterOrder(
        var userId: String? = null,
        var payMethod: String? = null,
        var receiverName: String? = null,
        var receiverPhone: String? = null,
        var receiverZipcode: String? = null,
        var receiverAddress1: String? = null,
        var receiverAddress2: String? = null,
        var etcMessage: String? = null,
        var orderItemList: List<RegisterOrderItem>? = null
    ) {
        constructor() : this(
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }

    data class RegisterOrderItem(
        var orderCount: Int? = null,
        var itemToken: String? = null,
        var itemName: String? = null,
        var itemPrice: Long? = null,
        var orderItemOptionGroupList: List<RegisterOrderItemOptionGroup>? = null
    ) {
        constructor() : this(
            null,
            null,
            null,
            null,
            null
        )
    }

    data class RegisterOrderItemOptionGroup(
        var ordering: Int? = null,
        var itemOptionGroupName: String? = null,
        var orderItemOptionList: List<RegisterOrderItemOption>? = null
    ) {
        constructor() : this(
            null,
            null,
            null
        )
    }

    data class RegisterOrderItemOption(
        var ordering: Int? = null,
        var itemOptionName: String? = null,
        var itemOptionPrice: Long? = null
    ) {
        constructor() : this(
            null,
            null,
            null
        )
    }

}