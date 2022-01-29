package msa.order.domain.order.item

import msa.order.domain.AbstractEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class OrderItem(
    @Id var id: String,
    var orderCount: Int,
    var partnerId: String,
    var itemId: String,
    var itemName: String,
    var itemToken: String,
    var itemPrice: Long,
    var orderItemOptionGroupList: List<OrderItemOptionGroup>,
    var deliveryStatus: DeliveryStatus
) : AbstractEntity() {
    enum class DeliveryStatus(description: String) {
        BEFORE_DELIVERY("배송전"),
        DELIVERY_PREPARE("배송준비중"),
        DELIVERING("배송중"),
        COMPLETE_DELIVERY("배송완료")
    }
}