package msa.order.domain.item.optiongroup

import msa.order.domain.AbstractEntity
import msa.order.domain.item.option.ItemOption
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ItemOptionGroup(
    val id: Long? = null,
    val itemOptionGroupName: String? = null,
    val itemOptionList: List<ItemOption>? = null,
) : AbstractEntity() {

}