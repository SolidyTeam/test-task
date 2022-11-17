package am.solidy.core.repository

interface MainRepository {
    suspend fun fetchData()
}