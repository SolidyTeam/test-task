package am.solidy.task.ui.user_details

import am.solidy.task.core.base.BaseViewModel
import am.solidy.task.core.extensions.getOrThrow
import am.solidy.task.ui.user_details.adapter.UserDetailItem
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userDetailsInteractor: UserDetailsInteractor,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val args = savedStateHandle.getOrThrow<UserDetailsArgs>()

    private val _userDetails = MutableStateFlow<List<UserDetailItem>>(emptyList())
    val userDetails = _userDetails.asStateFlow()

    init {
        getUserDetails()
    }

    private fun getUserDetails() = userDetailsInteractor.getUserWithPosts(args.userId)
        .onEach { userDetails ->
            _userDetails.value = userDetails
        }
        .catch { }
        .launchIn(viewModelScope)

}