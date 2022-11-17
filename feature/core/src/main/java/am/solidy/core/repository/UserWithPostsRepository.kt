package am.solidy.core.repository

import am.solidy.core.entity.UserWithPostsEntity
import kotlinx.coroutines.flow.Flow

interface UserWithPostsRepository {
    fun getUsers(): Flow<List<UserWithPostsEntity>>
    fun getUserWithPosts(userId: Int): Flow<UserWithPostsEntity?>
}