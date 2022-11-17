package am.solidy.data.repository

import am.solidy.core.repository.PostsRepository
import am.solidy.data.dp.dao.PostDao
import am.solidy.data.dp.mapper.MapperPostResponseToDb
import am.solidy.data.network.api.PostsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepositoryImpl @Inject constructor(
    private val api: PostsApi,
    private val dao: PostDao
) : PostsRepository {

    override suspend fun fetchPosts() {
        val posts = api.getPosts()
        dao.insertPosts(
            MapperPostResponseToDb().map(posts)
        )
    }

}