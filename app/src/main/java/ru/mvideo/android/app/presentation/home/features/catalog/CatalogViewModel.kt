package ru.mvideo.android.app.presentation.home.features.catalog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import kotlinx.coroutines.launch
import ru.mvideo.android.data.model.category.Category
import ru.mvideo.android.domain.CategoriesInteractor
import javax.inject.Inject

internal class CatalogViewModel @Inject constructor(
    private val interactor: CategoriesInteractor
) : ViewModel() {

    private val mutableLiveData = MutableLiveData<List<Category>>()
    private var getCatalogsDisposable = Disposables.disposed()
    val catalogsLiveData: LiveData<List<Category>>
        get() = mutableLiveData


    fun getAllCategories() {
        viewModelScope.launch {
            getCatalogsDisposable.dispose()
            getCatalogsDisposable =
                interactor.getCategories()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.wtf("Presenter taken catalogs ", "$it")
                        mutableLiveData.value = it
                    }, {})
        }
    }

    fun onParentCategoryClicked(id: Long, isOpen: Boolean) {
        val oldCategories: List<Category>? = mutableLiveData.value

        val newCategories: List<Category>? =
            oldCategories?.apply {
                this.find { it.id == id }?.isSelected = isOpen
            }
        viewModelScope.launch {
            Log.wtf("New categories", "InCatalogPresenter ${newCategories ?: "NULL"}")
            newCategories?.let { mutableLiveData.value = it }
        }
    }
}