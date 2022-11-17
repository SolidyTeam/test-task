package am.solidy.core

import am.solidy.core.delegate.EventDelegate
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DelegateModule {

    @Provides
    @Singleton
    fun provideEventDelegate() = EventDelegate()
}