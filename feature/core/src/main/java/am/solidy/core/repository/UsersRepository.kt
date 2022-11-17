package am.solidy.core.repository

interface UsersRepository {
    suspend fun fetchUsers()
}