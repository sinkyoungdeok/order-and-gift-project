package msa.order.interfaces.order.gift

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

class GiftOrderDto {
    class RegisterOrderRequest(
        @field:NotNull(message = "buyerUserId 는 필수값 입니다")
        var buyerUserId: String,

        @field:NotBlank(message = "payMethod 는 필수값 입니다")
        var payMethod: String,

        var orderItemList: List<RegisterOrderItemRequest>,


        var receiverName: String = "TEMP_VALUE",
        var receiverPhone: String = "TEMP_VALUE",
        var receiverZipcode: String = "TEMP_VALUE",
        var receiverAddress1: String = "TEMP_VALUE",
        var receiverAddress2: String = "TEMP_VALUE",
        var etcMessage: String = "TEMP_VALUE"
    )

    class RegisterOrderItemRequest(
        @field:NotNull(message = "buyerUserId 는 필수값 입니다")
        var orderCount: Int,

        @field:NotBlank(message = "itemToken 는 필수값입니다")
        var itemToken: String,

        @field:NotBlank(message = "itemName 는 필수값입니다")
        var itemName: String,

        @field:NotNull(message = "itemPrice 는 필수값입니다")
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
        var itemOptionPrice: String
    )

    class RegisterResponse(
        var orderToken: String
    ) {
        constructor() : this("")
    }
}