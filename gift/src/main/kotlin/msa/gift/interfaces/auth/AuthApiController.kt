package msa.gift.interfaces.auth

import msa.gift.application.auth.AuthFacade
import msa.gift.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/v1/auth")
class AuthApiController(
    val authFacade: AuthFacade,
    val authDtoMapper: AuthDtoMapper
) {

    @PostMapping("/login")
    suspend fun login(@RequestBody request: AuthDto.LoginRequest): CommonResponse<AuthDto.LoginResponse> {
        val token = authFacade.login(request.loginId, request.password)
        val response = authDtoMapper.of(token)
        return CommonResponse(response)
    }

    @PostMapping("/reissue")
    suspend fun reissue(@RequestBody request: AuthDto.ReissueRequest): CommonResponse<AuthDto.LoginResponse> {
        val command = authDtoMapper.of(request)
        val token = authFacade.reissue(command)
        val response = authDtoMapper.of(token)
        return CommonResponse(response)
    }

    @PostMapping("/logout")
    fun logout(principal: Principal): CommonResponse<String> {
        authFacade.logout(principal.name)
        return CommonResponse("ok")
    }
}