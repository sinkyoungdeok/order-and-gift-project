package msa.order.interfaces.order

class OrderDto {

    data class RegisterOrderRequest(
        var userId: String? = null,
        var payMethod: String? = null,
        var receiverName: String? = null,
        var receiverPhone: String? = null,
        var receiverZipcode: String? = null,
        var receiverAddress1: String? = null,
        var receiverAddress2: String? = null,
        var etcMessage: String? = null,
        var orderItemList: List<RegisterOrderItemRequest>? = null
    )

    data class RegisterOrderItemRequest(
        var orderCount: Int? = null,
        var itemToken: String? = null,
        var itemName: String? = null,
        var itemPrice: Long? = null,
        var orderItemOptionGroupList: List<RegisterOrderItemOptionGroupRequest>? = null
    )

    data class RegisterOrderItemOptionGroupRequest(
        var ordering: Int? = null,
        var itemOptionGroupName: String? = null,
        var orderItemOptionList: List<RegisterOrderItemOptionRequest>? = null
    )

    data class RegisterOrderItemOptionRequest(
        var ordering: Int? = null,
        var itemOptionName: String? = null,
        var itemOptionPrice: Long? = null
    )

}