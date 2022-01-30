package msa.order.infrastructure.partner


import kotlinx.coroutines.reactor.awaitSingle
import msa.order.domain.partner.Partner
import msa.order.domain.partner.PartnerReader
import org.springframework.stereotype.Component

@Component
class PartnerReaderImpl(
    val partnerRepository: PartnerRepository
) : PartnerReader {
    override suspend fun getPartner(partnerToken: String): Partner {
        return partnerRepository.findByPartnerToken(partnerToken).awaitSingle()
    }
}

