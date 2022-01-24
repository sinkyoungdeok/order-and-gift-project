package msa.order.infrastructure.partner


import msa.order.domain.partner.Partner
import msa.order.domain.partner.PartnerReader
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class PartnerReaderImpl(
    val partnerRepository: PartnerRepository
) : PartnerReader {
    override fun getPartner(partnerToken: Mono<String>): Mono<Partner> {
        return partnerRepository.findByPartnerToken(partnerToken)
    }
}