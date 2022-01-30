package msa.order.infrastructure.partner


import kotlinx.coroutines.reactor.awaitSingleOrNull
import msa.order.common.exception.EntityNotFoundException
import msa.order.domain.partner.Partner
import msa.order.domain.partner.PartnerReader
import org.springframework.stereotype.Component

@Component
class PartnerReaderImpl(
    val partnerRepository: PartnerRepository
) : PartnerReader {
    override suspend fun getPartner(partnerToken: String): Partner {
        return partnerRepository.findByPartnerToken(partnerToken).awaitSingleOrNull()
            ?: throw EntityNotFoundException()
    }
}

