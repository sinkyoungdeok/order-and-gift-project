package msa.order.domain

data class PartnerInfo(var id: Long,
                       var partnerToken: String,
                       var partnerName: String,
                       var businessNo: String,
                       var email: String,
                       var status: Partner.Status)