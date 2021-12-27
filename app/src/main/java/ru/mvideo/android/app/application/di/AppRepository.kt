package ru.mvideo.android.app.application.di

import dagger.Module
import dagger.Provides
import ru.mvideo.android.data.repository.RepositoryProvider
import ru.mvideo.android.data.repository.catalog.CatalogRepository
import javax.inject.Singleton

@Module(includes = [AppRepository.Provider::class])
object AppRepository {

    @Module
    object Provider {

        @JvmStatic
        @Provides
        @Singleton
        fun articleRepository(
            repositoryProvider: RepositoryProvider
        ): CatalogRepository = repositoryProvider.catalogRepository

    }
}