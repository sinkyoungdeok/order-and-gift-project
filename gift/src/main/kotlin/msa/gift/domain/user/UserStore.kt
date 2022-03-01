package msa.gift.domain.user

interface UserStore {
    suspend fun store(user: User): User
}