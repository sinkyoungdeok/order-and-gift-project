package msa.order.interfaces.partner

import msa.order.domain.partner.PartnerCommand
import msa.order.domain.partner.PartnerInfo
import msa.order.domain.user.UserCommand
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface PartnerDtoMapper {

    fun of(request: PartnerDto.RegisterRequest): PartnerCommand.RegisterPartner

    fun of(request: PartnerDto.RegisterUserRequest): UserCommand.RegisterPartnerRequest

    fun of(partnerInfo: PartnerInfo.Main): PartnerDto.RegisterResponse
}