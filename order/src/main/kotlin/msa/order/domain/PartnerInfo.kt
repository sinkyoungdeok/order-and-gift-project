package msa.order.domain

data class PartnerInfo(
    var id: String,
    var partnerToken: String,
    var partnerName: String,
    var businessNo: String,
    var email: String,
    var status: Partner.Status
) {
    constructor(partner: Partner) : this(
        partner.id,
        "",
        partner.partnerName,
        partner.businessNo,
        partner.email,
        partner.status
    )
}