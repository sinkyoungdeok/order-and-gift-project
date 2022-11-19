package msa.order.interfaces.user

import msa.order.User
import msa.order.UserServiceGrpcKt
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GrpcUserService : UserServiceGrpcKt.UserServiceCoroutineImplBase() {

    companion object {
        val log = LoggerFactory.getLogger(GrpcUserService::class.java)!!
    }

    override suspend fun registerUser(request: User.RegisterUserRequest): User.RegisterUserResponse {
        log.info(request.loginId)

        return User.RegisterUserResponse.newBuilder().build()
    }
}