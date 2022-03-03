package msa.gift.interfaces.admin

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
interface AdminDtoMapper {

    fun of(
        request: AdminDto.RegisterAdminRequest
    ): UserCommand.RegisterAdminRequest

    fun of(
        userInfo: UserInfo.Main
    ): AdminDto.RegisterAdminResponse

    fun of(
        userInfo: UserInfo.RoleInfo
    ): AdminDto.RegisterRoleResponse
}