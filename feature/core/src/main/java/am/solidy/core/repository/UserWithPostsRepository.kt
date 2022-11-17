package am.solidy.core.repository

import am.solidy.core.entity.UserWithPostsEntity
import kotlinx.coroutines.flow.Flow

interface UserWithPostsRepository {
    fun getUserWithPosts(userId: Int): Flow<UserWithPostsEntity?>
    fun getUsersWithPosts(): Flow<List<UserWithPostsEntity>>
}