package msa.order.domain.partner

import msa.order.application.partner.event.PartnerEmailEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Partner(
    @Id
    var id: String? = null,
    var partnerToken: String,
    var partnerName: String,
    var businessNo: String,
    var email: String,
    var status: Status? = null
) {
    enum class Status(description: String) {
        ENABLE("활성화"),
        DISABLE("비활성화")
    }
}