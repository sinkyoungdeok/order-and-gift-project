package msa.order.domain.item.option

import msa.order.domain.AbstractEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class ItemOption(
    @Id var id: String? = null,
    var ordering: Int,
    var itemOptionName: String,
    var itemOptionPrice: Long
) : AbstractEntity() {

}