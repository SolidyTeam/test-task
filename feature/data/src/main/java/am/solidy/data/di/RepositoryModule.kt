package am.solidy.data.di

import am.solidy.core.repository.PostsRepository
import am.solidy.core.repository.UserWithPostsRepository
import am.solidy.core.repository.UsersRepository
import am.solidy.data.repository.PostsRepositoryImpl
import am.solidy.data.repository.UserWithPostsRepositoryImpl
import am.solidy.data.repository.UsersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUsersRepository(impl: UsersRepositoryImpl): UsersRepository

    @Binds
    fun bindPostsRepository(impl: PostsRepositoryImpl): PostsRepository

    @Binds
    fun bindUserWithPostsRepository(impl: UserWithPostsRepositoryImpl): UserWithPostsRepository

}