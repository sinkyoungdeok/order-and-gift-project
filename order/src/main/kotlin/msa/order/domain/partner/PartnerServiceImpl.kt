package msa.order.domain.partner

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Mono

@Service
class PartnerServiceImpl(
    val partnerStore: PartnerStore,
    val partnerInfoMapper: PartnerInfoMapper
) :
    PartnerService {

    @Transactional
    override fun registerPartner(command: PartnerCommand.RegisterPartner): Mono<PartnerInfo.Main> {
        var initPartner = command.toEntity()
        var partner = partnerStore.store(initPartner)
        return partner.map { partnerInfoMapper.of(it) }
    }
}