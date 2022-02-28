package msa.order.interfaces.user

import msa.order.common.response.CommonResponse
import msa.order.domain.user.UserCommand
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/user")
class UserApiController(
    val userDtoMapper: UserDtoMapper
) {

    @PostMapping
    suspend fun registerUser(
        @Valid @RequestBody request: UserDto.RegisterUserRequest
    ): CommonResponse<UserCommand.RegisterUserRequest> {
        var command = userDtoMapper.of(request)
        return CommonResponse(command)
    }
}