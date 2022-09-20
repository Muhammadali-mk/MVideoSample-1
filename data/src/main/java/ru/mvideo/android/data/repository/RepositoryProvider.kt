package ru.mvideo.android.data.repository

import ru.mvideo.android.data.repository.catalog.CatalogRepository

interface RepositoryProvider {

    val catalogRepository: CatalogRepository
    // new added repo
    // new branch
}