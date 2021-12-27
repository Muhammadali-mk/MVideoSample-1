package ru.mvideo.android.data.provider

import ru.mvideo.android.data.repository.RepositoryProvider
import ru.mvideo.android.data.repository.RepositoryProviderImpl

class DataProvider {

    val repositoryProvider: RepositoryProvider by lazy {
        return@lazy RepositoryProviderImpl()
    }
}