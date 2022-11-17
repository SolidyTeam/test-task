package am.solidy.data.dp.entity

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithPostsDbEntity(
    @Embedded val user: UserDbEntity,
    @Relation(
        parentColumn =  "id",
        entityColumn = "userId"
    )
    val posts: List<PostDbEntity>
)