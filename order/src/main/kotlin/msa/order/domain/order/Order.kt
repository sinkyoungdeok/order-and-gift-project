package msa.order.domain.order

import msa.order.domain.AbstractEntity
import msa.order.domain.order.fragment.DeliveryFragment
import msa.order.domain.order.item.OrderItem
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class Order(
    @Id var id: String? = null,
    var orderToken: String,
    var userId: String,
    var payMethod: String,
    var orderItemList: List<OrderItem>,
    var deliveryFragment: DeliveryFragment,
    var orderedAt: LocalDateTime,
    var status: Status
) : AbstractEntity() {
    enum class Status(description: String) {
        INIT("주문시작"),
        ORDER_COMPLETE("주문완료"),
        DELIVERY_PREPARE("배송준비"),
        IN_DELIVERY("배송중"),
        DELIVERY_COMPLETE("배송완료")
    }
}