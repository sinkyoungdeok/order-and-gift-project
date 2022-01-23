package msa.order.interfaces.order

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class OrderDto {

    data class RegisterOrderRequest(
        @field:NotNull(message = "userId 는 필수값입니다")
        var userId: String? = null,

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

    data class RegisterOrderItemRequest(
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

    data class RegisterOrderItemOptionGroupRequest(
        @field:NotNull(message = "ordering 는 필수값입니다")
        var ordering: Int? = null,

        @field:NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        var itemOptionGroupName: String? = null,

        var orderItemOptionList: List<RegisterOrderItemOptionRequest>? = null
    )

    data class RegisterOrderItemOptionRequest(
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
        constructor(): this(null)
    }
}