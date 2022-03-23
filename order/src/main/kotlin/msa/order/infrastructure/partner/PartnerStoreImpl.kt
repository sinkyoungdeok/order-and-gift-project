package msa.order.infrastructure.partner

import kotlinx.coroutines.reactor.awaitSingle
import msa.order.common.exception.InvalidParamException
import msa.order.domain.partner.Partner
import msa.order.domain.partner.PartnerStore
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component

@Component
class PartnerStoreImpl(
    val partnerRepository: PartnerRepository
) : PartnerStore {
    override suspend fun store(initPartner: Partner): Partner {
        if (StringUtils.isEmpty(initPartner.partnerToken)) throw InvalidParamException("param.partnerToken")
        if (StringUtils.isEmpty(initPartner.businessNo)) throw InvalidParamException("partner.businessNo")
        if (StringUtils.isEmpty(initPartner.email)) throw InvalidParamException("partner.email")

        return partnerRepository.save(initPartner).awaitSingle()
    }
}