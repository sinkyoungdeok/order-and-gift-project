package msa.order.interfaces.partner

import msa.order.domain.partner.Partner
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class PartnerDto {

    data class RegisterRequest(
        @field:NotEmpty(message = "partnerName 은 필수값 입니다")
        var partnerName: String,

        @field:NotEmpty(message = "businessNo 는 필수값 입니다")
        var businessNo: String,

        @field:Email(message = "email 형식에 맞추어야 합니다")
        @field:NotEmpty(message = "email 은 필수값 입니다")
        var email: String
    )

    data class RegisterResponse(
        var partnerToken: String,
        var partnerName: String,
        var businessNo: String,
        var email: String,
        var status: Partner.Status? = null
    ) {
        constructor() : this("", "", "", "", Partner.Status.ENABLE)
    }
}