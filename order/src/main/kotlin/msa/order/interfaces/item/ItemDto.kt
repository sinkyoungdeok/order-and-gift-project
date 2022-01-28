package msa.order.interfaces.item

class ItemDto {

    class RegisterItemRequest(
        val partnerToken: String,
        val itemName: String,
        val itemPrice: Long,
        val itemOptionGroupList: List<RegisterItemOptionGroupRequest>
    )

    class RegisterItemOptionGroupRequest(
        val ordering: Int,
        val itemOptionGroupName: String,
        val itemOptionList: List<RegisterItemOptionRequest>
    )

    class RegisterItemOptionRequest(
        val ordering: Int,
        val itemOptionName: String,
        val itemOptionPrice: Long
    )

    class RegisterResponse(
        val itemToken: String
    )
}