package msa.order.interfaces.user

import msa.order.ReactorUserServiceGrpc
import msa.order.User
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class GrpcUserService: ReactorUserServiceGrpc.UserServiceImplBase() {

    companion object {
        val log = LoggerFactory.getLogger(GrpcUserService::class.java)!!
    }

    override fun registerUser(request: Mono<User.RegisterUserRequest>): Mono<User.RegisterUserResponse>? {
        val res = request.flatMap {
            log.info("${it.loginId}")
            Mono.just(User.RegisterUserResponse.newBuilder().build())
        }

        return res
    }
}