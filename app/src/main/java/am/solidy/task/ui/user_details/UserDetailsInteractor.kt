package am.solidy.task.ui.user_details

import am.solidy.core.repository.UserWithPostsRepository
import am.solidy.task.ui.user_details.adapter.UserDetailItem
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class UserDetailsInteractor @Inject constructor(
    private val userWithPostsRepository: UserWithPostsRepository
) {

    fun getUserWithPosts(userId: Int) = userWithPostsRepository.getUserWithPosts(userId)
        .filterNotNull()
        .map { userWithPosts ->
            val userDetails = UserDetailItem.UserDetails(
                user = userWithPosts.user,
                postCount = userWithPosts.posts.size
            )
            val posts = userWithPosts.posts.map { UserDetailItem.Post(it) }
            buildList {
                add(userDetails)
                addAll(posts)
            }
        }
        .flowOn(Dispatchers.IO)

}