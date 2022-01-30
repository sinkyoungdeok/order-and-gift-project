package msa.order.domain.order.item

import msa.order.domain.AbstractEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class OrderItem(
    @Id var id: String? = null,
    var orderCount: Int,
    var partnerId: String,
    var itemId: String,
    var itemName: String,
    var itemToken: String,
    var itemPrice: Long,
    var orderItemOptionGroupList: List<OrderItemOptionGroup> = arrayListOf(),
    var deliveryStatus: DeliveryStatus
) : AbstractEntity() {
    constructor(
        orderCount: Int,
        partnerId: String,
        itemId: String,
        itemToken: String,
        itemName: String,
        itemPrice: Long
    ) : this(
        null,
        orderCount,
        partnerId,
        itemId,
        itemName,
        itemToken,
        itemPrice,
        arrayListOf(),
        DeliveryStatus.BEFORE_DELIVERY
    )

    enum class DeliveryStatus(val description: String) {
        BEFORE_DELIVERY("배송전"),
        DELIVERY_PREPARE("배송준비중"),
        DELIVERING("배송중"),
        COMPLETE_DELIVERY("배송완료")
    }

    fun calculateTotalAmount(): Long {
        var itemOptionTotalAmount = orderItemOptionGroupList.stream()
            .mapToLong(OrderItemOptionGroup::calculateTotalAmount)
            .sum()
        return (itemPrice + itemOptionTotalAmount) * orderCount
    }
}