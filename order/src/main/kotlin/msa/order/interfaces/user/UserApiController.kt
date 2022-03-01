package msa.order.interfaces.user

import msa.order.application.user.UserFacade
import msa.order.common.response.CommonResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/user")
class UserApiController(
    val userDtoMapper: UserDtoMapper,
    val userFacade: UserFacade
) {
    @PostMapping
    suspend fun registerUser(
        @Valid @RequestBody request: UserDto.RegisterUserRequest
    ): CommonResponse<UserDto.RegisterUserResponse> {
        val command = userDtoMapper.of(request)
        val userInfo = userFacade.registerUser(command)
        val response = userDtoMapper.of(userInfo)
        return CommonResponse(response)
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('PARTNER')")
    suspend fun retrieveUser(
        principal: Principal
    ): CommonResponse<UserDto.RegisterUserResponse> {
        val username = principal.name
        val userInfo = userFacade.retrieveUser(username)
        val response = userDtoMapper.of(userInfo)
        return CommonResponse(response)
    }

    @PatchMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('PARTNER')")
    suspend fun quitUser(
        principal: Principal
    ): CommonResponse<UserDto.RegisterUserResponse> {
        val username = principal.name
        val userInfo = userFacade.quitUser(username)
        val response = userDtoMapper.of(userInfo)
        return CommonResponse(response)
    }
}