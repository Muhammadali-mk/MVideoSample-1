package ru.mvideo.android.domain

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.mvideo.android.data.model.category.Category
import ru.mvideo.android.data.repository.catalog.CatalogRepository
import javax.inject.Inject

class CategoriesInteractor @Inject constructor(
    private val catalogRepository: CatalogRepository
) {

    fun getProducts(): Single<List<Category>> {
        return catalogRepository.getCategories()
            .subscribeOn(Schedulers.io())
    }
}