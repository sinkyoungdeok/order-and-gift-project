package msa.order.domain.partner

interface PartnerStore {
    suspend fun store(initPartner: Partner): Partner
}