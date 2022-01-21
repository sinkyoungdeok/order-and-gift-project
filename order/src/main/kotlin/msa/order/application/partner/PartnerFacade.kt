package msa.order.application.partner

import msa.order.domain.PartnerInfo
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class PartnerFacade {

    fun registerPartner(): Flux<PartnerInfo> {
        return Flux.just(PartnerInfo(1), PartnerInfo(2))
    }
}