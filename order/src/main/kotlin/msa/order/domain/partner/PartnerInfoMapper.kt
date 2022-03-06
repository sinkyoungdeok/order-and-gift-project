package msa.order.domain.partner

import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface PartnerInfoMapper {

    fun of(partner: Partner): PartnerInfo.Main
}