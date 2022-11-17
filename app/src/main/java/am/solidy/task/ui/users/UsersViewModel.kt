package am.solidy.task.ui.users

import am.solidy.core.entity.UserWithPostsEntity
import am.solidy.task.core.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

    private val _users = MutableStateFlow<List<UserWithPostsEntity>>(emptyList())
    val users = _users.asStateFlow()

    init {
        getUsers()
    }

    private fun getUsers() = usersInteractor.getUsers()
        .onEach { users ->
            _users.value = users
        }
        .catch { }
        .launchIn(viewModelScope)

    fun navigateToUserDetails(userWithPostsEntity: UserWithPostsEntity) {

    }

}