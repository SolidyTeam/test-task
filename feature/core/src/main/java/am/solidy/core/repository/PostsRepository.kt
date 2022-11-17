package am.solidy.core.repository

interface PostsRepository {
    suspend fun fetchPosts()
}