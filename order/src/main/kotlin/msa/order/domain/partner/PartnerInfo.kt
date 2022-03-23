package msa.order.domain.partner

class PartnerInfo {

    class Main(
        var id: String,
        var partnerToken: String,
        var businessNo: String,
        var email: String,
        var status: Partner.Status? = null
    ) {
        constructor() : this( "", "", "", "", Partner.Status.ENABLE)
    }

}