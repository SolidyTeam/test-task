package am.solidy.data.network.api

import am.solidy.data.network.entity.post.Post
import retrofit2.http.GET

interface PostApi {

    @GET("SharminSirajudeen/test_resources/posts")
    suspend fun getPosts(): List<Post>

}