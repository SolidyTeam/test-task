package am.solidy.data.repository

import am.solidy.core.repository.UsersRepository
import am.solidy.data.dp.dao.UserDao
import am.solidy.data.dp.mapper.MapperUserResponseToDb
import am.solidy.data.network.api.UsersApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepositoryImpl @Inject constructor(
    private val api: UsersApi,
    private val dao: UserDao
) : UsersRepository {


    override suspend fun fetchUsers() {
        val users = api.getUsers()
        dao.insertUsers(
            MapperUserResponseToDb().map(users)
        )
    }

}