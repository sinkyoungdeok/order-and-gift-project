package msa.order.domain.partner

import reactor.core.publisher.Mono

interface PartnerStore {
    fun store(initPartner: Mono<Partner>): Mono<Partner>
}