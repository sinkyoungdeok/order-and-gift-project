package msa.order.domain.order.item

import msa.order.domain.AbstractEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
class OrderItemOption(
    @Id var id: String? = null,
    var ordering: Int,
    var itemOptionName: String,
    var itemOptionPrice: Long
) : AbstractEntity() {
    constructor(ordering: Int, itemOptionName: String, itemOptionPrice: Long) : this(
        null,
        ordering,
        itemOptionName,
        itemOptionPrice
    )
}