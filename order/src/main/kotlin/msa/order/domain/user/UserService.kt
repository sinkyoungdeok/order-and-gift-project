package msa.order.domain.user

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*
import javax.annotation.PostConstruct

@Service
class UserService {

    var data: MutableMap<String, User> = HashMap<String, User>()

    @PostConstruct
    fun init() {
        //username:passwowrd -> user:user
        data["user"] = User(
            "user",
            "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=",
            true,
            Arrays.asList(Role.ROLE_USER)
        )

        //username:passwowrd -> admin:admin
        data["admin"] = User(
            "admin",
            "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=",
            true,
            Arrays.asList(Role.ROLE_ADMIN)
        )
    }

    fun findByUsername(username: String?): Mono<User?> {
        return Mono.justOrEmpty(data[username])
    }
}