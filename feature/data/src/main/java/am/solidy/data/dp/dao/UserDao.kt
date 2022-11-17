package am.solidy.data.dp.dao

import am.solidy.data.dp.entity.UserDbEntity
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserDbEntity>)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<UserDbEntity>>

}