package msa.order.domain.item

class ItemCommand {

    data class RegisterItemRequest(
        var itemName: String? = null,
        var itemPrice: Long? = null,
        var itemOptionGroupRequestList: List<RegisterItemOptionGroupRequest>? = null
    )

    data class RegisterItemOptionGroupRequest(
        var ordering: Int? = null,
        var itemOptionGroupName: String? = null,
        var itemOptionRequestList: List<RegisterItemOptionRequest>? = null,
    )

    data class RegisterItemOptionRequest(
        var ordering: Int? = null,
        var itemOptionName: String? = null,
        var itemOptionPrice: Long? = null
    )
}