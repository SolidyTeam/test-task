package am.solidy.core.entity

data class UserEntity(
    val id: Int,
    val albumId: Int?,
    val name: String,
    val imageUrl: String,
    val thumbnailUrl: String
)