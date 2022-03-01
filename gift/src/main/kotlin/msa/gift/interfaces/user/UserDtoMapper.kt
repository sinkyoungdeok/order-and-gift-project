package msa.gift.interfaces.user

import msa.gift.domain.user.UserCommand
import msa.gift.domain.user.UserInfo
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