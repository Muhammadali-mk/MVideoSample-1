package ru.mvideo.android.data.repository

import ru.mvideo.android.data.repository.catalog.CatalogRepository
import ru.mvideo.android.data.repository.catalog.CatalogRepositoryImpl

internal class RepositoryProviderImpl() : RepositoryProvider {

    override val catalogRepository: CatalogRepository by lazy {
        CatalogRepositoryImpl()
    }
}