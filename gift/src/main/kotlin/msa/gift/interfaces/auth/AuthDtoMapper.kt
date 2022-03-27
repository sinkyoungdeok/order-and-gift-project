package msa.gift.interfaces.auth

import msa.gift.domain.user.UserInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface AuthDtoMapper {
    fun of(userInfo: UserInfo.Token): AuthDto.LoginResponse
}