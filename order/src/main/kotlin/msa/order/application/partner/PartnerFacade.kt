package msa.order.application.partner

import msa.order.domain.partner.PartnerCommand
import msa.order.domain.partner.PartnerInfo
import msa.order.domain.partner.PartnerService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PartnerFacade(val partnerService: PartnerService) {

    fun registerPartner(command: Mono<PartnerCommand.RegisterPartner>): Mono<PartnerInfo.Main> {
        val partnerInfo = partnerService.registerPartner(command)
        return partnerInfo
    }
}