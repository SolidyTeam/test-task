package am.solidy.data.dp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
class PostDbEntity(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String
)