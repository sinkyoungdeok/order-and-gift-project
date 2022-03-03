package msa.order.interfaces.admin

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