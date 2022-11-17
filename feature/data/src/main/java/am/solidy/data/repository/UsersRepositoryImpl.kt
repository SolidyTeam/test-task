package am.solidy.data.repository

import am.solidy.core.entity.UserEntity
import am.solidy.core.repository.UsersRepository
import am.solidy.data.dp.dao.UserDao
import am.solidy.data.dp.mapper.MapperUserDbEntityToDomain
import am.solidy.data.dp.mapper.MapperUserResponseToDb
import am.solidy.data.network.api.UsersApi
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    override suspend fun getUsers(): Flow<List<UserEntity>> = dao.getUsers().map { users ->
        MapperUserDbEntityToDomain().map(users)
    }

}