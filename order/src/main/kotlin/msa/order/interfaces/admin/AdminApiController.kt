package msa.order.interfaces.admin

import msa.order.application.admin.AdminFacade
import msa.order.application.user.UserFacade
import msa.order.common.response.CommonResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/admin")
class AdminApiController(
    val adminDtoMapper: AdminDtoMapper,
    val adminFacade: AdminFacade,
    val userFacade: UserFacade
) {
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun registerAdmin(
        @Valid @RequestBody request: AdminDto.RegisterAdminRequest
    ): CommonResponse<AdminDto.RegisterAdminResponse> {
        val command = adminDtoMapper.of(request)
        val userInfo = adminFacade.registerAdmin(command)
        val response = adminDtoMapper.of(userInfo)
        return CommonResponse(response)
    }

    @GetMapping("/{loginId}")
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun retrieveUser(
        @PathVariable loginId: String
    ): CommonResponse<AdminDto.RegisterAdminResponse> {
        val userInfo = userFacade.retrieveUser(loginId)
        val response = adminDtoMapper.of(userInfo)
        return CommonResponse(response)
    }

    @PatchMapping("/{loginId}/quit")
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun quitUser(
        @PathVariable loginId: String
    ): CommonResponse<AdminDto.RegisterAdminResponse> {
        val userInfo = userFacade.quitUser(loginId)
        val response = adminDtoMapper.of(userInfo)
        return CommonResponse(response)
    }

    @PatchMapping("/{loginId}/come-back")
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun comeBackUser(
        @PathVariable loginId: String
    ): CommonResponse<AdminDto.RegisterAdminResponse> {
        val userInfo = userFacade.comeBackUser(loginId)
        val response = adminDtoMapper.of(userInfo)
        return CommonResponse(response)
    }
}