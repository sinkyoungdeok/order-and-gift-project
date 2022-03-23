package msa.order.domain.partner

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Partner(
    @Id
    var id: String? = null,
    var partnerToken: String,
    var businessNo: String,
    var email: String,
    var status: Status? = null,
    var userId: String,
) {
    enum class Status(description: String) {
        ENABLE("활성화"),
        DISABLE("비활성화")
    }
}