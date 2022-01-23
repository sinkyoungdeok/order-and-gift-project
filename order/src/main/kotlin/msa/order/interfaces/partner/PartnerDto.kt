package msa.order.interfaces.partner

import msa.order.domain.partner.Partner
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class PartnerDto {

    data class RegisterRequest(
        @field:NotEmpty(message = "partnerName 은 필수값 입니다")
        var partnerName: String? = null,

        @field:NotEmpty(message = "businessNo 는 필수값 입니다")
        var businessNo: String? = null,

        @field:Email(message = "email 형식에 맞추어야 합니다")
        @field:NotEmpty(message = "email 은 필수값 입니다")
        var email: String? = null
    )

    data class RegisterResponse(
        var partnerToken: String? = null,
        var partnerName: String,
        var businessNo: String,
        var email: String,
        var status: Partner.Status
    ) {
        constructor() : this("", "", "", "", Partner.Status.ENABLE)
    }
}