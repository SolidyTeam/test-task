package am.solidy.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserDbEntity(
    @PrimaryKey
    val id: Int,
    val albumId: Int?,
    val name: String,
    val imageUrl: String,
    val thumbnailUrl: String
)