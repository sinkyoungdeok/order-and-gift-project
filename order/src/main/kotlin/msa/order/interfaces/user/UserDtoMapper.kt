package msa.order.interfaces.user

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
interface UserDtoMapper {

    fun of(
        request: UserDto.RegisterUserRequest
    ): UserCommand.RegisterUserRequest

    fun of(
        userInfo: UserInfo.Main
    ): UserDto.RegisterUserResponse

    fun of(
        userInfo: UserInfo.RoleInfo
    ): UserDto.RegisterRoleResponse
}