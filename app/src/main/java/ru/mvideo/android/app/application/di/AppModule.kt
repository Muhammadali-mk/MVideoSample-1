package ru.mvideo.android.app.application.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mvideo.android.app.application.di.AppModule.Provider
import ru.mvideo.android.data.provider.DataProvider
import ru.mvideo.android.data.repository.RepositoryProvider
import javax.inject.Singleton

@Module(includes = [Provider::class])
object AppModule {

    @Module
    object Provider {

        @JvmStatic
        @Provides
        @Singleton
        fun provideDataProvider(
            context: Context
        ): DataProvider = DataProvider()

        @JvmStatic
        @Provides
        @Singleton
        fun provideRepositoryProvider(
            dataProvider: DataProvider
        ): RepositoryProvider = dataProvider.repositoryProvider
    }
}