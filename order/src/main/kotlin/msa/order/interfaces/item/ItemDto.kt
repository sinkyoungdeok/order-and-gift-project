package msa.order.interfaces.item

class ItemDto {

    data class RegisterItemRequest(
        var partnerToken: String? = null,
        var itemName: String? = null,
        var itemPrice: Long? = null,
        var itemOptionGroupList: List<RegisterItemOptionGroupRequest>? = null
    )

    data class RegisterItemOptionGroupRequest(
        var ordering: Int? = null,
        var itemOptionGroupName: String? = null,
        var itemOptionList: List<RegisterItemOptionRequest>? = null
    )

    data class RegisterItemOptionRequest(
        var ordering: Int? = null,
        var itemOptionName: String? = null,
        var itemOptionPrice: Long? = null
    )
}