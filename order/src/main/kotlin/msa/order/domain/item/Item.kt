package msa.order.domain.item

import msa.order.domain.AbstractEntity
import msa.order.domain.item.optiongroup.ItemOptionGroup
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Item(
    @Id var id: String? = null,
    var itemToken: String,
    var partnerId: String,
    var itemName: String,
    var itemPrice: Long,
    var itemOptionGroupList: List<ItemOptionGroup>,
    var status: Status = Status.PREPARE
) : AbstractEntity() {
    enum class Status(description: String) {
        PREPARE("판매준비중"),
        ON_SALE("판매중"),
        END_OF_SALE("판매종료")
    }

    fun changeOnSale() {
        this.status = Status.ON_SALE
    }
}
