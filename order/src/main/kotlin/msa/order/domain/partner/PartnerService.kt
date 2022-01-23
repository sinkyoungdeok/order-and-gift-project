package msa.order.domain.partner

import reactor.core.publisher.Mono

interface PartnerService {
    fun registerPartner(command: Mono<PartnerCommand.RegisterPartner>): Mono<PartnerInfo.Main>
}