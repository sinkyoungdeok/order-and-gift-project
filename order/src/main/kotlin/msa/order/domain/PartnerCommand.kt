package msa.order.domain

class PartnerCommand{

    data class RegisterPartner(
        var partnerName: String,
        var businessNo: String,
        var email: String
    ) {
        fun toEntity(): Partner {
            return Partner(partnerName, businessNo, email)
        }
    }
}