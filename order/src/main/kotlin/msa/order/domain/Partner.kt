package msa.order.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Partner(
    @Id
    var id: String? = null,
    var partnerToken: String? = null,
    var partnerName: String? = null,
    var businessNo: String? = null,
    var email: String? = null,
    var status: Status? = null
) {
    enum class Status(description: String) {
        ENABLE("활성화"),
        DISABLE("비활성화")
    }

    constructor(partnerName: String, businessNo: String, email: String) : this(
        null,
        null,
        partnerName,
        businessNo,
        email,
        Partner.Status.ENABLE
    )
}