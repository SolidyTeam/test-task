package am.solidy.core.entity

data class UserWithPostsEntity(
    val user: UserEntity,
    val posts: List<PostEntity>
)