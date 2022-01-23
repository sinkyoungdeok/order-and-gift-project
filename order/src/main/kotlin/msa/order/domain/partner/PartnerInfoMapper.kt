package msa.order.domain.partner

import org.mapstruct.*

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface PartnerInfoMapper {

    fun of(partner: Partner): PartnerInfo.Main


    @Mappings(
        Mapping(target = "id", ignore = true),
        Mapping(target = "partnerToken", ignore = true),
        Mapping(target = "status", ignore = true)
    )
    fun of(command: PartnerCommand.RegisterPartner): Partner
}