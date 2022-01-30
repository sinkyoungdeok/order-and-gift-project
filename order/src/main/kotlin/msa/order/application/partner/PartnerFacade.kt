package msa.order.application.partner

import msa.order.domain.partner.PartnerCommand
import msa.order.domain.partner.PartnerInfo
import msa.order.domain.partner.PartnerService
import org.springframework.stereotype.Service

@Service
class PartnerFacade(
    val partnerService: PartnerService
) {
    suspend fun registerPartner(command: PartnerCommand.RegisterPartner): PartnerInfo.Main {
        val partnerInfo = partnerService.registerPartner(command)
        return partnerInfo
    }
}