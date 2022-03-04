package msa.order.domain.user


interface UserReader {
    suspend fun getUserBy(username: String): User?
}