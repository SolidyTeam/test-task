package am.solidy.data.dp.dao

import am.solidy.data.dp.entity.PostDbEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostDbEntity>)

}