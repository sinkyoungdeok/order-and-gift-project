package msa.gift.interfaces.user

import msa.gift.application.user.UserFacade
import msa.gift.common.response.CommonResponse
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
        val loginId = principal.name
        val userInfo = userFacade.retrieveUser(loginId)
        val response = userDtoMapper.of(userInfo)
        return CommonResponse(response)
    }

    @PatchMapping("/quit")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('PARTNER')")
    suspend fun quitUser(
        principal: Principal
    ): CommonResponse<UserDto.RegisterUserResponse> {
        val loginId = principal.name
        val userInfo = userFacade.quitUser(loginId)
        val response = userDtoMapper.of(userInfo)
        return CommonResponse(response)
    }

    @PatchMapping("/come-back")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('PARTNER')")
    suspend fun comeBackUser(
        principal: Principal
    ): CommonResponse<UserDto.RegisterUserResponse> {
        val loginId = principal.name
        val userInfo = userFacade.comeBackUser(loginId)
        val response = userDtoMapper.of(userInfo)
        return CommonResponse(response)
    }
}