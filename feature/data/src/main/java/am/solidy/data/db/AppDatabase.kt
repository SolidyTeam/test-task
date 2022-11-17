package am.solidy.data.db

import am.solidy.data.db.dao.PostDao
import am.solidy.data.db.dao.UserDao
import am.solidy.data.db.dao.UserWithPostsDao
import am.solidy.data.db.entity.PostDbEntity
import am.solidy.data.db.entity.UserDbEntity
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        UserDbEntity::class,
        PostDbEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val postDao: PostDao
    abstract val userWithPostsDao: UserWithPostsDao

    companion object {
        private const val NAME = "test-task-db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, NAME)
                .fallbackToDestructiveMigration()
                .build()

    }
}