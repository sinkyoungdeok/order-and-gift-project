package msa.order.domain.partner

class PartnerCommand {

    data class RegisterPartner(
        var partnerName: String,
        var businessNo: String,
        var email: String
    ) {
        constructor() : this("","","")
    }
}