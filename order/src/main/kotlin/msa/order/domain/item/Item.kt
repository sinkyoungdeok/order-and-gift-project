package msa.order.domain.item

import msa.order.domain.AbstractEntity
import msa.order.domain.item.optiongroup.ItemOptionGroup
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Item(
    @Id val id: String? = null,
    val itemToken: String,
    val partnerId: String,
    val itemName: String,
    val itemPrice: Long,
    val itemOptionGroupList: List<ItemOptionGroup>,
    val status: Status? = null
) : AbstractEntity() {
    enum class Status(description: String) {
        PREPARE("판매준비중"),
        ON_SALE("판매중"),
        END_OF_SALE("판매종료")
    }
}
