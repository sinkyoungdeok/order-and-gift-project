package msa.order.interfaces.auth

import msa.order.domain.user.UserCommand
import msa.order.domain.user.UserInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.ERROR
)
interface AuthDtoMapper {
    fun of(request: AuthDto.ReissueRequest): UserCommand.ReissueTokenRequest
    fun of(userInfo: UserInfo.Token): AuthDto.LoginResponse
}