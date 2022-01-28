package msa.order.interfaces.item

import msa.order.domain.item.Item

class ItemDto {

    // register
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

    // change
    class ChangeStatusItemRequest(
        var itemToken: String
    )

    // retrieve
    class Main(
        var itemToken: String,
        var partnerId: String,
        var itemName: String,
        var itemPrice: Long,
        var status: Item.Status,
        var itemOptionGroupList: List<ItemOptionGroupInfo>
    )

    class ItemOptionGroupInfo(
        var ordering: Int,
        var itemOptionGroupName: String,
        var itemOptionList: List<ItemOptionInfo>
    )

    class ItemOptionInfo(
        var ordering: Int,
        var itemOptionName: String,
        var itemOptionPrice: Long
    )
}