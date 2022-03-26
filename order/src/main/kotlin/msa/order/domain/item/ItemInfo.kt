package msa.order.domain.item

class ItemInfo {

    class Token(
        var itemToken: String
    ) {
        constructor() : this("")
    }

    class Main(
        var itemToken: String,
        var partnerName: String,
        var itemName: String,
        var itemPrice: Long,
        var status: Item.Status,
        var itemOptionGroupList: List<ItemOptionGroupInfo>
    ) {

    }

    class ItemOptionGroupInfo(
        var ordering: Int,
        var itemOptionGroupName: String,
        var itemOptionList: List<ItemOptionInfo>
    ) {

    }

    class ItemOptionInfo(
        var ordering: Int,
        var itemOptionName: String,
        var itemOptionPrice: Long
    ) {

    }
}