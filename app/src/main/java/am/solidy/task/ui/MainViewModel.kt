package am.solidy.task.ui

import am.solidy.core.delegate.Event
import am.solidy.core.delegate.EventDelegate
import am.solidy.task.core.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainInteractor: MainInteractor,
    private val eventDelegate: EventDelegate
) : BaseViewModel() {

    private val _showErrorMessage = Channel<String>(Channel.BUFFERED)
    val showErrorMessage = _showErrorMessage.receiveAsFlow()

    init {
        viewModelScope.launch {
            mainInteractor.fetchData()
        }
        viewModelScope.launch {
            eventDelegate.events.filter { it is Event.MessageEvent }
                .onEach {
                    _showErrorMessage.send((it as Event.MessageEvent).message)
                }.collect()
        }
    }

}