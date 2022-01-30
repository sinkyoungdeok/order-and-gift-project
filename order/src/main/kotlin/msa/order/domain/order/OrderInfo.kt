package msa.order.domain.order

import java.time.LocalDateTime

class OrderInfo {

    class Token(
        var orderToken: String? = null
    ) {
        constructor() : this(null)
    }

    class Main(
        var orderId: String,
        var orderToken: String,
        var userId: String,
        var payMethod: String,
        var totalAmount: Long,
        var deliveryInfo: DeliveryInfo,
        var orderedAt: LocalDateTime,
        var status: String,
        var statusDescription: String,
        var orderItemList: List<OrderItemInfo>
    ) {
        constructor() : this(
            "",
            "",
            "",
            "",
            0,
            DeliveryInfo(),
            LocalDateTime.now(),
            "",
            "",
            arrayListOf()
        )
    }

    class DeliveryInfo(
        var receiverName: String,
        var receiverPhone: String,
        var receiverZipcode: String,
        var receiverAddress1: String,
        var receiverAddress2: String,
        var etcMessage: String
    ) {
        constructor() : this(
            "",
            "",
            "",
            "",
            "",
            ""
        )
    }

    class OrderItemInfo(
        var orderCount: Int,
        var partnerId: String,
        var itemId: String,
        var itemName: String,
        var totalAmount: Long,
        var itemPrice: Long,
        var deliveryStatus: String,
        var deliveryStatusDescription: String,
        var orderItemOptionGroupList: List<OrderItemOptionGroupInfo>
    ) {
        constructor() : this(
            0,
            "",
            "",
            "",
            0,
            0,
            "",
            "",
            arrayListOf()
        )
    }

    class OrderItemOptionGroupInfo(
        var ordering: Int,
        var itemOptionGroupName: String,
        var orderItemOptionList: List<OrderItemOptionInfo>
    ) {
        constructor() : this(
            0,
            "",
            arrayListOf()
        )
    }

    class OrderItemOptionInfo(
        var ordering: Int,
        var itemOptionName: String,
        var itemOptionPrice: Long
    ) {
        constructor() : this(
            0,
            "",
            0
        )
    }
}