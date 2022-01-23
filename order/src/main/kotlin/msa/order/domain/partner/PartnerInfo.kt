package msa.order.domain.partner

class PartnerInfo {

    data class Main(
        var id: String,
        var partnerToken: String? = null,
        var partnerName: String,
        var businessNo: String,
        var email: String,
        var status: Partner.Status
    ) {
        constructor(partner: Partner) : this(
            partner.id ?: "",
            partner.partnerToken ?: "",
            partner.partnerName ?: "",
            partner.businessNo ?: "",
            partner.email ?: "",
            partner.status ?: Partner.Status.ENABLE
        )

        constructor() : this("", "", "", "", "", Partner.Status.ENABLE)
    }

}