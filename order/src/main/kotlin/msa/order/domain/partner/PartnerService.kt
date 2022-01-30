package msa.order.domain.partner

import reactor.core.publisher.Mono

interface PartnerService {
    suspend fun registerPartner(
        command: PartnerCommand.RegisterPartner
    ): PartnerInfo.Main
}