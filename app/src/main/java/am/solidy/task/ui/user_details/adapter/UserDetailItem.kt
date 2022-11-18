package am.solidy.task.ui.user_details.adapter

import am.solidy.core.entity.PostEntity
import am.solidy.core.entity.UserEntity

sealed class UserDetailItem(val id: Any) {

    data class UserDetails(
        val user: UserEntity,
        val postCount: Int,
        val followersCount: Int = 0,
        val followingCount: Int = 0
    ) : UserDetailItem(user.id)

    data class Post(val post: PostEntity) : UserDetailItem(post.id * post.userId)

}
