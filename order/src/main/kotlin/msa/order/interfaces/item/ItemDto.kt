package msa.order.interfaces.item

class ItemDto {

    class RegisterItemRequest(
        var partnerToken: String,
        var itemName: String,
        var itemPrice: Long,
        var itemOptionGroupList: List<RegisterItemOptionGroupRequest>
    )

    class RegisterItemOptionGroupRequest(
        var ordering: Int,
        var itemOptionGroupName: String,
        var itemOptionList: List<RegisterItemOptionRequest>
    )

    class RegisterItemOptionRequest(
        var ordering: Int,
        var itemOptionName: String,
        var itemOptionPrice: Long
    )

    class RegisterResponse(
        var itemToken: String
    )

    class ChangeStatusItemRequest(
        var itemToken: String
    )
}