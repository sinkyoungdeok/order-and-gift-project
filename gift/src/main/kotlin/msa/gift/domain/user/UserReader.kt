package msa.gift.domain.user

interface UserReader {
    suspend fun getUserBy(loginId: String): User?
}