package am.solidy.task.ui

import am.solidy.task.core.base.BaseViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainInteractor: MainInteractor
) : BaseViewModel() {

    fun fetchData() = viewModelScope.launch {
        mainInteractor.fetchData()
    }

}