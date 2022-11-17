package am.solidy.data.dp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserDbEntity(
    @PrimaryKey
    val id: Int
)