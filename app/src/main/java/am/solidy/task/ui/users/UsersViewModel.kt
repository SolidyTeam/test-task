package am.solidy.task.ui.users

import am.solidy.core.delegate.EventDelegate
import am.solidy.core.entity.UserWithPostsEntity
import am.solidy.core.extensions.onEachMessageEvent
import am.solidy.task.core.base.BaseViewModel
import am.solidy.task.core.navigation.Command
import am.solidy.task.ui.user_details.UserDetailsArgs
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersInteractor: UsersInteractor,
    private val eventDelegate: EventDelegate,
) : BaseViewModel() {

    private val _users = MutableStateFlow<List<UserWithPostsEntity>>(emptyList())
    val users = _users.asStateFlow()

    private val _showErrorMessage = MutableStateFlow("")
    val showErrorMessage = _showErrorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        getUsers()
        fetchData()
        collectErrorMessages()
    }

    fun fetchData() = viewModelScope.launch {
        _isLoading.value = true
        usersInteractor.fetchData()
    }

    private fun getUsers() {
        usersInteractor.getUsers()
            .onStart { _isLoading.value = true }
            .onEach { users ->
                _users.value = users
                if (users.isNotEmpty()) {
                    _showErrorMessage.value = ""
                    _isLoading.value = false
                }
            }
            .catch { _isLoading.value = false }
            .launchIn(viewModelScope)
    }

    fun navigateToUserDetails(userWithPostsEntity: UserWithPostsEntity) {
        val args = UserDetailsArgs(userWithPostsEntity.user.id)
        val dir = UsersFragmentDirections.actionToUserDetails(args)
        sendCommand(Command.NavCommand(dir))
    }

    private fun collectErrorMessages() {
        eventDelegate.events
            .onEachMessageEvent {
                _isLoading.value = false
                _showErrorMessage.value = it.message
            }.launchIn(viewModelScope)
    }

}