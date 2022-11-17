package am.solidy.task.ui

import am.solidy.core.repository.PostsRepository
import am.solidy.core.repository.UsersRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainInteractor @Inject constructor(
    private val usersRepository: UsersRepository,
    private val postsRepository: PostsRepository
) {

    suspend fun fetchData() = withContext(Dispatchers.IO) {
        launch { usersRepository.fetchUsers() }
        launch { postsRepository.fetchPosts() }
    }

}