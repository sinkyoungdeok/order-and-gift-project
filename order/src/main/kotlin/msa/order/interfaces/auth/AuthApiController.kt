package msa.order.interfaces.auth

import msa.order.common.jwt.JwtUtil
import msa.order.common.jwt.PBKDF2Encoder
import msa.order.domain.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/v1/auth")
class AuthApiController(
    val jwtUtil: JwtUtil,
    val passwordEncoder: PBKDF2Encoder,
    val userService: UserService
) {

    @PostMapping("/login")
    fun login(@RequestBody request: AuthDto.LoginRequest): Mono<ResponseEntity<AuthDto.LoginResponse>> {
        return userService.findByUsername(request.username)
            .filter { userDetails ->
                passwordEncoder.encode(request.password).equals(userDetails!!.password)
            }
            .map { userDetails ->
                ResponseEntity.ok(
                    AuthDto.LoginResponse(
                        jwtUtil.generateToken(
                            userDetails!!
                        )
                    )
                )
            }
            .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()))
    }
}