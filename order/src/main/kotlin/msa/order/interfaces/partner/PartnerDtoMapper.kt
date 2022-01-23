package msa.order.interfaces.partner

import msa.order.domain.PartnerCommand
import msa.order.domain.PartnerInfo
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy


@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface PartnerDtoMapper {

    fun of(request: PartnerDto.RegisterRequest): PartnerCommand.RegisterPartner

    fun of(partnerInfo: PartnerInfo.Main): PartnerDto.RegisterResponse
}