package am.solidy.data.repository

import am.solidy.core.repository.MainRepository
import am.solidy.data.db.AppDatabase
import am.solidy.data.db.mapper.MapperPostResponseToDb
import am.solidy.data.db.mapper.MapperUserResponseToDb
import am.solidy.data.network.api.PostApi
import am.solidy.data.network.api.UserApi
import androidx.room.withTransaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val userApi: UserApi,
    private val postApi: PostApi,
) : MainRepository {
    private val userDao = database.userDao
    private val postDao = database.postDao

    override suspend fun fetchData() {
        withContext(Dispatchers.IO) {
            val usersJob = async { userApi.getUsers() }
            val postsJob = async { postApi.getPosts() }

            val users = usersJob.await()
            val posts = postsJob.await()

            database.withTransaction {
                userDao.insertUsers(
                    MapperUserResponseToDb().map(users)
                )
                postDao.insertPosts(
                    MapperPostResponseToDb().map(posts)
                )
            }
        }
    }
}