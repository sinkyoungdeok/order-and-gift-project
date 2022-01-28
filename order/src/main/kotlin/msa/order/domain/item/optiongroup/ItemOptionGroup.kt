package msa.order.domain.item.optiongroup

import msa.order.domain.AbstractEntity
import msa.order.domain.item.option.ItemOption
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class ItemOptionGroup(
    @Id var id: String? = null,
    var ordering: Int,
    var itemOptionGroupName: String,
    var itemOptionList: List<ItemOption>,
) : AbstractEntity() {

}