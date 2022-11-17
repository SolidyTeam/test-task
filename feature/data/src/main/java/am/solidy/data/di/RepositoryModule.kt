package am.solidy.data.di

import am.solidy.core.repository.MainRepository
import am.solidy.core.repository.UserWithPostsRepository
import am.solidy.data.repository.MainRepositoryImpl
import am.solidy.data.repository.UserWithPostsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUsersRepository(impl: MainRepositoryImpl): MainRepository

    @Binds
    fun bindUserWithPostsRepository(impl: UserWithPostsRepositoryImpl): UserWithPostsRepository

}