package msa.order.domain.item.optiongroup

import msa.order.domain.AbstractEntity
import msa.order.domain.item.option.ItemOption
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ItemOptionGroup(
    @Id val id: String? = null,
    val ordering: Int? = null,
    val itemOptionGroupName: String? = null,
    val itemOptionList: List<ItemOption>? = null,
) : AbstractEntity() {

}