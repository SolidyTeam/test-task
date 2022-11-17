package am.solidy.data.network.api

import am.solidy.data.network.entity.user.User
import retrofit2.http.GET

interface UserApi {

    @GET("SharminSirajudeen/test_resources/users")
    suspend fun getUsers(): List<User>

}