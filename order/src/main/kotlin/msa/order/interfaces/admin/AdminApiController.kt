package msa.order.interfaces.admin

import msa.order.application.admin.AdminFacade
import msa.order.common.response.CommonResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/admin")
class AdminApiController(
    val adminDtoMapper: AdminDtoMapper,
    val adminFacade: AdminFacade
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
}