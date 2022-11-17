package am.solidy.data.dp.dao

import am.solidy.data.dp.entity.UserWithPostsDbEntity
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserWithPostsDao {

    @Query("SELECT * FROM users WHERE id = :userId")
    fun getUserWithPosts(userId: Int): Flow<UserWithPostsDbEntity?>

    @Query("SELECT * FROM users")
    fun getUsersWithPosts(): Flow<List<UserWithPostsDbEntity>>

}