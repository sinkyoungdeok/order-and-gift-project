package msa.order.domain

import reactor.core.publisher.Mono

interface PartnerService {
    fun registerPartner(command: Mono<PartnerCommand.RegisterPartner>): Mono<PartnerInfo>
}