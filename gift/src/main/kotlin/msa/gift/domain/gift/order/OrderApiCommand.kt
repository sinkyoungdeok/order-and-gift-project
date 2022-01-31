package msa.gift.domain.gift.order

class OrderApiCommand {
    class RegisterOrder(
        var buyerUserId: String,
        var payMethod: String,
        var orderItemList: List<RegisterOrderItem>
    )

    class RegisterOrderItem(
        var orderCount: Int,
        var itemToken: String,
        var itemName: String,
        var itemPrice: String,
        var orderItemOptionGroupList: List<RegisterOrderItemOptionGroup>
    )

    class RegisterOrderItemOptionGroup(
        var ordering: Int,
        var itemOptionGroupName: String,
        var orderItemOptionList: List<RegisterOrderItemOption>
    )

    class RegisterOrderItemOption(
        var ordering: Int,
        var itemOptionName: String,
        var itemOptionPrice: Long
    )
}