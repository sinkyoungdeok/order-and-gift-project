package msa.order.domain.partner

import reactor.core.publisher.Mono

interface PartnerReader {
    fun getPartner(partnerToken: String): Mono<Partner>
}