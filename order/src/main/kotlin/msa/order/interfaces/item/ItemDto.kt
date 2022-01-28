package msa.order.interfaces.item

class ItemDto {

    data class RegisterItemRequest(
        var partnerToken: String,
        var itemName: String,
        var itemPrice: Long,
        var itemOptionGroupList: List<RegisterItemOptionGroupRequest>
    )

    data class RegisterItemOptionGroupRequest(
        var ordering: Int,
        var itemOptionGroupName: String,
        var itemOptionList: List<RegisterItemOptionRequest>
    )

    data class RegisterItemOptionRequest(
        var ordering: Int,
        var itemOptionName: String,
        var itemOptionPrice: Long
    )

    class RegisterResponse(
        var itemToken: String? = null
    )
}