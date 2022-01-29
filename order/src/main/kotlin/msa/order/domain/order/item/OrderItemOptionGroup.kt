package msa.order.domain.order.item

import msa.order.domain.AbstractEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class OrderItemOptionGroup(
    @Id var id: String,
    var ordering: Int,
    var itemOptionGroupName: String,
    var orderItemOptionList: List<OrderItemOption>
) : AbstractEntity() {
}