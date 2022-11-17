package am.solidy.core.repository

import am.solidy.core.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun fetchUsers()
    suspend fun getUsers(): Flow<List<UserEntity>>
}