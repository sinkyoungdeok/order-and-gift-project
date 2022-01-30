package msa.order.infrastructure.partner

import kotlinx.coroutines.reactor.awaitSingle
import msa.order.domain.partner.Partner
import msa.order.domain.partner.PartnerStore
import org.springframework.stereotype.Component

@Component
class PartnerStoreImpl(
    val partnerRepository: PartnerRepository
) : PartnerStore {
    override suspend fun store(initPartner: Partner): Partner {
        return partnerRepository.save(initPartner).awaitSingle()
    }
}