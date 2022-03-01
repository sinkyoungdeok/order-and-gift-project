package msa.order.domain.user

import msa.order.common.jwt.PBKDF2Encoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    val userStore: UserStore,
    val userReader: UserReader,
    val pbkdF2Encoder: PBKDF2Encoder
) : UserService {

    @Transactional
    override suspend fun registerUser(command: UserCommand.RegisterUserRequest): UserInfo.Main {
        var initUser = command.toEntity(pbkdF2Encoder)
        var user = userStore.store(initUser)
        return UserInfo.Main(user)
    }

    @Transactional(readOnly = true)
    override suspend fun retrieveUser(username: String): UserInfo.Main {
        var user = userReader.getUserBy(username)
        return UserInfo.Main(user)
    }

    @Transactional
    override suspend fun quitUser(username: String): UserInfo.Main {
        var user = userReader.getUserBy(username)
        user.quit()
        user = userStore.store(user)
        return UserInfo.Main(user)
    }

    @Transactional
    override suspend fun comeBackUser(username: String): UserInfo.Main {
        var user = userReader.getUserBy(username)
        user.comeBack()
        user = userStore.store(user)
        return UserInfo.Main(user)
    }
}