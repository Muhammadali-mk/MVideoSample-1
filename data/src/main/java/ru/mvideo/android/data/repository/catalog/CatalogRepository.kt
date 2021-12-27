package ru.mvideo.android.data.repository.catalog

import io.reactivex.Single
import ru.mvideo.android.data.model.category.Category

interface CatalogRepository {

    fun getCategories(): Single<List<Category>>
}