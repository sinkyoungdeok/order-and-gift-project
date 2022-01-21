package msa.order.application.partner

import msa.order.domain.PartnerInfo
import msa.order.domain.PartnerService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class PartnerFacade(val partnerService: PartnerService) {

    fun registerPartner(): Flux<PartnerInfo> {
        val partnerInfo = partnerService.registerPartner()
        return partnerInfo
    }
}