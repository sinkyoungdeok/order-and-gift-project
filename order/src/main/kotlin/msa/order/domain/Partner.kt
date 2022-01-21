package msa.order.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Partner {
    @Id
    lateinit var id: String
    var partnerToken: String? = null
    lateinit var partnerName: String
    lateinit var businessNo: String
    lateinit var email: String
    lateinit var status: Status

    enum class Status(description: String) {
        ENABLE("활성화"),
        DISABLE("비활성화")
    }
}

