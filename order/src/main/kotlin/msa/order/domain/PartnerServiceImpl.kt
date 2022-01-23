package msa.order.domain

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PartnerServiceImpl(val partnerStore: PartnerStore) : PartnerService {
    override fun registerPartner(command: Mono<PartnerCommand.RegisterPartner>): Mono<PartnerInfo.Main> {
        var initPartner = command.map { it.toEntity() }
        var partner = partnerStore.store(initPartner)
        return partner.map { PartnerInfo.Main(it) }
    }
}