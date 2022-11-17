package am.solidy.data.repository

import am.solidy.core.entity.UserWithPostsEntity
import am.solidy.core.repository.UserWithPostsRepository
import am.solidy.data.db.dao.UserWithPostsDao
import am.solidy.data.db.mapper.MapperUserWithPostsDbEntityToDomain
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class UserWithPostsRepositoryImpl @Inject constructor(
    private val dao: UserWithPostsDao
) : UserWithPostsRepository {

    override fun getUserWithPosts(userId: Int): Flow<UserWithPostsEntity?> =
        dao.getUserWithPosts(userId).map { userWithPosts ->
            userWithPosts?.let { MapperUserWithPostsDbEntityToDomain().map(userWithPosts) }
        }

    override fun getUsers(): Flow<List<UserWithPostsEntity>> =
        dao.getUsersWithPosts().map { usersWithPosts ->
            MapperUserWithPostsDbEntityToDomain().map(usersWithPosts)
        }
}