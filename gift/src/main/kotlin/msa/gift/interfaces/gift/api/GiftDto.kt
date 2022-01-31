package msa.gift.interfaces.gift.api

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class GiftDto {
    class RegisterOrderRequest(
        @field:NotNull(message = "userId 는 필수값입니다")
        var buyerUserId: String,

        @field:NotBlank(message = "payMethod 는 필수값입니다")
        var payMethod: String,

        @field:NotBlank(message = "pushType 는 필수값입니다")
        var pushType: String,

        @field:NotBlank(message = "giftReceiverName 는 필수값입니다")
        var giftReceiverName: String,

        @field:NotBlank(message = "giftReceiverPhone 는 필수값입니다")
        var giftReceiverPhone: String,

        @field:NotBlank(message = "giftMessage 는 필수값입니다")
        var giftMessage: String,

        var orderItemList: List<RegisterOrderItemRequest>
    )

    class RegisterOrderItemRequest(
        @field:NotNull(message = "orderCount 는 필수값입니다")
        var orderCount: Int,

        @field:NotBlank(message = "itemToken 는 필수값입니다")
        var itemToken: String,

        @field:NotBlank(message = "itemName 는 필수값입니다")
        var itemName: String,

        @field:NotBlank(message = "itemPrice 는 필수값입니다")
        var itemPrice: Long,

        var orderItemOptionGroupList: List<RegisterOrderItemOptionGroupRequest>
    )

    class RegisterOrderItemOptionGroupRequest(
        @field:NotNull(message = "ordering 는 필수값입니다")
        var ordering: Int,

        @field:NotBlank(message = "itemOptionGroupName 는 필수값입니다")
        var itemOptionGroupName: String,

        var orderItemOptionList: List<RegisterOrderItemOptionRequest>
    )

    class RegisterOrderItemOptionRequest(
        @field:NotNull(message = "ordering 는 필수값입니다")
        var ordering: Int,

        @field:NotBlank(message = "itemOptionName 는 필수값입니다")
        var itemOptionName: String,

        @field:NotNull(message = "itemOptionPrice 는 필수값입니다")
        var itemOptionPrice: Long
    )

    class RegisterOrderResponse(
        var orderToken: String,
        var giftToken: String
    )
}