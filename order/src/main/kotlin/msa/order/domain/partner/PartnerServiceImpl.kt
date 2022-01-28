package msa.order.domain.partner

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class PartnerServiceImpl(
    val partnerStore: PartnerStore,
    val partnerInfoMapper: PartnerInfoMapper
) :
    PartnerService {
    override fun registerPartner(command: PartnerCommand.RegisterPartner): Mono<PartnerInfo.Main> {
        var initPartner = partnerInfoMapper.of(command)
        var partner = partnerStore.store(initPartner)
        return partner.map { partnerInfoMapper.of(it) }
    }
}