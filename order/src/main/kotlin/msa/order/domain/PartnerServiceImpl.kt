package msa.order.domain

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class PartnerServiceImpl : PartnerService {
    override fun registerPartner(): Flux<PartnerInfo> {
        return Flux.just(PartnerInfo(1), PartnerInfo(2), PartnerInfo(3))
    }
}