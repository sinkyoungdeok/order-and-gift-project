package msa.order.interfaces.partner

import msa.order.domain.Partner
import msa.order.domain.PartnerCommand
import msa.order.domain.PartnerInfo
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
    ) {
        fun toCommand(): PartnerCommand.RegisterPartner {
            return PartnerCommand.RegisterPartner(partnerName ?: "", businessNo ?: "", email ?: "")
        }
    }

    data class RegisterResponse(
        var partnerToken: String,
        var partnerName: String,
        var businessNo: String,
        var email: String,
        var status: Partner.Status
    ) {
        constructor(partnerInfo: PartnerInfo.Main) : this(
            partnerInfo.partnerToken,
            partnerInfo.partnerName,
            partnerInfo.businessNo,
            partnerInfo.email,
            partnerInfo.status
        )
        constructor(): this("","","","",Partner.Status.ENABLE)
    }
}