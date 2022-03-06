package msa.order.application.partner

import msa.order.application.partner.event.PartnerEmailEvent
import msa.order.domain.partner.PartnerCommand
import msa.order.domain.partner.PartnerInfo
import msa.order.domain.partner.PartnerService
import msa.order.domain.user.UserCommand
import msa.order.domain.user.UserService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PartnerFacade(
    val partnerService: PartnerService,
    val userService: UserService,
    val eventPublisher: ApplicationEventPublisher
) {
    suspend fun registerPartner(
        partnerCommand: PartnerCommand.RegisterPartner,
        userCommand: UserCommand.RegisterPartnerRequest
    ): PartnerInfo.Main {
        val userInfo = userService.registerPartner(userCommand)
        val partnerInfo = partnerService.registerPartner(partnerCommand, userInfo.id)
        eventPublisher.publishEvent(PartnerEmailEvent(partnerInfo.email))
        return partnerInfo
    }
}