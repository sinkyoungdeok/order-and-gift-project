package msa.order.domain

import reactor.core.publisher.Flux

interface PartnerService {
    fun registerPartner(): Flux<PartnerInfo>
}