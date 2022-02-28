package msa.order.domain.user

interface UserStore {
    suspend fun store(user: User): User
}