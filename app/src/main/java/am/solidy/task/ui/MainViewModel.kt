package am.solidy.task.ui

import am.solidy.core.delegate.EventDelegate
import am.solidy.core.extensions.onEachMessageEvent
import am.solidy.task.core.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val eventDelegate: EventDelegate
) : BaseViewModel() {

    private val _showErrorMessage = Channel<String>(Channel.BUFFERED)
    val showErrorMessage = _showErrorMessage.receiveAsFlow()

    init {
        viewModelScope.launch {
            eventDelegate.events
                .onEachMessageEvent {
                    _showErrorMessage.send((it).message)
                }.collect()
        }
    }

}