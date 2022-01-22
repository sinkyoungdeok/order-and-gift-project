package msa.order.application.partner

import msa.order.domain.Partner
import msa.order.domain.PartnerCommand
import msa.order.domain.PartnerInfo
import msa.order.domain.PartnerService
import mu.KotlinLogging
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger {}
@Service
class PartnerFacade(val partnerService: PartnerService) {

    fun registerPartner(command: Mono<PartnerCommand.RegisterPartner>): Mono<PartnerInfo> {
//        val partnerInfo = partnerService.registerPartner(command)
//        val partnerInfo = Mono.just(PartnerInfo("1","","","","",Partner.Status.ENABLE))
        val partnerInfo = partnerService.registerPartner(Mono.just(PartnerCommand.RegisterPartner("1","2","3")))
        return partnerInfo
    }
}