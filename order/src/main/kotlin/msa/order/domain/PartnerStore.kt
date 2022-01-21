package msa.order.domain

import reactor.core.publisher.Mono

interface PartnerStore {
    fun store(initPartner: Mono<Partner>): Mono<Partner>
}