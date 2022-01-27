package msa.order.domain.item.option

import msa.order.domain.AbstractEntity
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ItemOption(
    @Id val id: String? = null,
    val ordering: Int? = null,
    val itemOptionName: String? = null,
    val itemOptionPrice: Long? = null
) : AbstractEntity() {

}