package msa.order.domain.partner

interface PartnerReader {
    suspend fun getPartner(partnerToken: String): Partner
}