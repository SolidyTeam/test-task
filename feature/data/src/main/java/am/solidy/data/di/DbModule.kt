package am.solidy.data.di

import am.solidy.data.dp.AppDatabase
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideRoomDb(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.userDao

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase) = db.postDao

    @Provides
    @Singleton
    fun provideUserWithPostsDao(db: AppDatabase) = db.userWithPostsDao

}