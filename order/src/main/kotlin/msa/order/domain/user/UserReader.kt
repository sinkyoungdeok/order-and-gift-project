package msa.order.domain.user


interface UserReader {
    suspend fun getUserBy(loginId: String): User?
}