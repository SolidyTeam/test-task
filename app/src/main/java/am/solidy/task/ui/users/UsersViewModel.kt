package am.solidy.task.ui.users

import am.solidy.core.entity.UserWithPostsEntity
import am.solidy.task.core.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor
) : BaseViewModel() {

    private val _users = MutableStateFlow<List<UserWithPostsEntity>?>(emptyList())
    val users = _users.asStateFlow()

    init {
        getUsers()
    }

    fun getUsers() {
        usersInteractor.getUsers()
            .onStart {
                _users.value = null
            }
            .onEach { users ->
                _users.value = users
            }
            .catch { }
            .launchIn(viewModelScope)
    }

    fun navigateToUserDetails(userWithPostsEntity: UserWithPostsEntity) {

    }

}