package msa.order.application.partner

import msa.order.application.partner.event.PartnerEmailEvent
import msa.order.domain.partner.PartnerCommand
import msa.order.domain.partner.PartnerInfo
import msa.order.domain.partner.PartnerService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PartnerFacade(
    val partnerService: PartnerService,
    val eventPublisher: ApplicationEventPublisher
) {
    suspend fun registerPartner(command: PartnerCommand.RegisterPartner): PartnerInfo.Main {
        val partnerInfo = partnerService.registerPartner(command)
        eventPublisher.publishEvent(PartnerEmailEvent(partnerInfo.email))
        return partnerInfo
    }
}