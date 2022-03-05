package msa.gift.interfaces.admin

import msa.gift.application.admin.AdminFacade
import msa.gift.application.user.UserFacade
import msa.gift.common.response.CommonResponse
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

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun retrieveUser(
        @PathVariable username: String
    ): CommonResponse<AdminDto.RegisterAdminResponse> {
        val userInfo = userFacade.retrieveUser(username)
        val response = adminDtoMapper.of(userInfo)
        return CommonResponse(response)
    }

    @PatchMapping("/{username}/quit")
    @PreAuthorize("hasRole('ADMIN')")
    suspend fun quitUser(
        @PathVariable username: String
    ): CommonResponse<AdminDto.RegisterAdminResponse> {
        val userInfo = userFacade.quitUser(username)
        val response = adminDtoMapper.of(userInfo)
        return CommonResponse(response)
    }
}