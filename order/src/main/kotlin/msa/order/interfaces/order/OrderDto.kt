package msa.order.interfaces.order

import msa.order.domain.order.payment.PayMethod
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class OrderDto {

    // register
    class RegisterOrderRequest(
        @field:NotBlank(message = "payMethod 는 필수값입니다")
        var payMethod: String? = null,

        @field:NotBlank(message = "receiverName 는 필수값입니다")
        var receiverName: String? = null,

        @field:NotBlank(message = "receiverPhone 는 필수값입니다")
        var receiverPhone: String? = null,

        @field:NotBlank(message = "receiverZipcode 는 필수값입니다")
        var receiverZipcode: String? = null,

        @field:NotBlank(message = "receiverAddress1 는 필수값입니다")
        var receiverAddress1: String? = null,

        @field:NotBlank(message = "receiverAddress2 는 필수값입니다")
        var receiverAddress2: String? = null,

        @field:NotBlank(message = "etcMessage 는 필수값입니다")
        var etcMessage: String? = null,

        var orderItemList: List<RegisterOrderItemRequest>? = null
    )

    class RegisterOrderItemRequest(
        @field:NotNull(message = "orderCount 는 필수값입니다")
        var orderCount: Int? = null,

        @field:NotBlank(message = "itemToken 는 필수값입니다")
        var itemToken: String? = null,

        @field:NotBlank(message = "itemName 는 필수값입니다")
        var itemName: String? = null,

        @field:NotBlank(message = "itemPrice 는 필수값입니다")
        var itemPrice: Long? = null,

        var orderItemOptionGroupList: List<RegisterOrderItemOptionGroupRequest>? = null
    )

    class RegisterOrderItemOptionGroupRequest(
        @field:NotNull(message = "ordering 는 필수값입니다")
        var ordering: Int? = null,

        @field:NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        var itemOptionGroupName: String? = null,

        var orderItemOptionList: List<RegisterOrderItemOptionRequest>? = null
    )

    class RegisterOrderItemOptionRequest(
        @field:NotNull(message = "ordering 는 필수값입니다")
        var ordering: Int? = null,

        @field:NotBlank(message = "itemOptionName 는 필수값입니다")
        var itemOptionName: String? = null,

        @field:NotBlank(message = "itemOptionPrice 는 필수값입니다")
        var itemOptionPrice: Long? = null
    )

    class RegisterOrderResponse(
        var orderToken: String? = null
    ) {
        constructor() : this(null)
    }

    // payment
    class PaymentRequest(
        @field:NotBlank(message = "orderToken 는 필수값입니다")
        var orderToken: String,

        @field:NotNull(message = "payMethod 는 필수값입니다")
        var payMethod: PayMethod,

        @field:NotNull(message = "amount 는 필수값입니다")
        var amount: Long,

        @field:NotBlank(message = "orderDescription 는 필수값입니다")
        var orderDescription: String
    ) {
        constructor() : this(
            "",
            PayMethod.CARD,
            0,
            ""
        )
    }

    // retrieve
    class Main(
        var orderToken: String,
        var userName: String,
        var payMethod: String,
        var totalAmount: Long,
        var deliveryInfo: DeliveryInfo,
        var orderedAt: String,
        var status: String,
        var statusDescription: String,
        var orderItemList: List<OrderItemInfo>
    ) {
        constructor() : this(
            "",
            "",
            "",
            0,
            DeliveryInfo(),
            "",
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
        var etcMessage: String,
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