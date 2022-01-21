package msa.order.application.partner

import msa.order.domain.PartnerCommand
import msa.order.domain.PartnerInfo
import msa.order.domain.PartnerService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class PartnerFacade(val partnerService: PartnerService) {

    fun registerPartner(command: Mono<PartnerCommand.RegisterPartner>): Flux<PartnerInfo> {
        val partnerInfo = partnerService.registerPartner()
        return partnerInfo
    }
}