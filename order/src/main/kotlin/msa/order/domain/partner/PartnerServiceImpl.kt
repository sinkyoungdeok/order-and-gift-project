package msa.order.domain.partner

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PartnerServiceImpl(
    val partnerStore: PartnerStore,
    val partnerInfoMapper: PartnerInfoMapper
) :
    PartnerService {

    @Transactional
    override suspend fun registerPartner(
        command: PartnerCommand.RegisterPartner,
        userId: String
    ): PartnerInfo.Main {
        var initPartner = command.toEntity(userId)
        var partner = partnerStore.store(initPartner)
        return partnerInfoMapper.of(partner)
    }
}