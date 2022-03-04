package msa.gift.domain.user

interface UserReader {
    suspend fun getUserBy(username: String): User?
}