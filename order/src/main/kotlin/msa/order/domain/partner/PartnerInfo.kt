package msa.order.domain.partner

class PartnerInfo {

    data class Main(
        var id: String,
        var partnerToken: String? = null,
        var partnerName: String,
        var businessNo: String,
        var email: String,
        var status: Partner.Status? = null
    ) {
        constructor() : this("", "", "", "", "", Partner.Status.ENABLE)
    }

}