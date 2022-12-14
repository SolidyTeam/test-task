package am.solidy.task.ui.users

import am.solidy.core.repository.MainRepository
import am.solidy.core.repository.UserWithPostsRepository
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class UsersInteractor @Inject constructor(
    private val usersWithPostsRepository: UserWithPostsRepository,
    private val mainRepository: MainRepository,
) {

    suspend fun fetchData() = mainRepository.fetchData()

    fun getUsers() = usersWithPostsRepository.getUsers()
        .flowOn(Dispatchers.IO)

}