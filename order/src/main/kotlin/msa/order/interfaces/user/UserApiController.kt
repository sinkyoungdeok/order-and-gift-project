package msa.order.interfaces.user

import msa.order.application.user.UserFacade
import msa.order.common.response.CommonResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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
    ): CommonResponse<UserDto.RegisterUserRequest> {
        val command = userDtoMapper.of(request)
        val userInfo = userFacade.registerUser(command)
        val response = userDtoMapper.of(userInfo)
        return CommonResponse(response)
    }
}