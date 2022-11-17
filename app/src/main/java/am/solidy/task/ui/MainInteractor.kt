package am.solidy.task.ui

import am.solidy.core.repository.MainRepository
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val mainRepository: MainRepository,
) {

    suspend fun fetchData() = mainRepository.fetchData()

}