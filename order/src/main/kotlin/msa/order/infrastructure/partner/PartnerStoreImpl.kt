package msa.order.infrastructure.partner

import msa.order.domain.Partner
import msa.order.domain.PartnerStore
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class PartnerStoreImpl(val partnerRepository: PartnerRepository) : PartnerStore {
    override fun store(initPartner: Mono<Partner>): Mono<Partner> {
        return initPartner.flatMap { partnerRepository.save(it) }
    }
}