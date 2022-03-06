package msa.order.domain.partner

interface PartnerService {
    suspend fun registerPartner(
        command: PartnerCommand.RegisterPartner,
        id: String,
    ): PartnerInfo.Main
}