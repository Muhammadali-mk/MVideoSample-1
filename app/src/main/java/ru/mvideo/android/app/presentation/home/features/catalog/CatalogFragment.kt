package ru.mvideo.android.app.presentation.home.features.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.mvideo.android.R
import ru.mvideo.android.app.presentation.home.features.catalog.controller.CategoryItemController
import ru.mvideo.android.app.presentation.home.features.catalog.controller.SearchItemController
import ru.mvideo.android.app.presentation.home.features.catalog.controller.SubCategoryItemController
import ru.mvideo.android.app.presentation.home.features.catalog.di.CatalogDaggerComponent
import ru.mvideo.android.data.model.category.Category
import ru.mvideo.android.databinding.FragmentProductBinding
import ru.surfstudio.android.easyadapter.EasyAdapter
import ru.surfstudio.android.easyadapter.ItemList
import javax.inject.Inject

internal class CatalogFragment : Fragment(R.layout.fragment_product),
    IHasComponent<CatalogDaggerComponent> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: CatalogViewModel by viewModels { viewModelFactory }

    private lateinit var binding: FragmentProductBinding
    private val adapter = EasyAdapter()

    private val categoryController =
        CategoryItemController { id, isOpen ->
            viewModel.onParentCategoryClicked(id, isOpen)
        }

    private val subCategoryController =
        SubCategoryItemController { }

    private val searchController = SearchItemController {
       //TODO should show top dialog in order to search
    }

    override fun getComponent(): CatalogDaggerComponent =
        CatalogDaggerComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        observeCatalogList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProductBinding.bind(view)

        with(binding) {
            catalogRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lm: RecyclerView.LayoutManager? = recyclerView.layoutManager
                    if ((lm as LinearLayoutManager).findFirstVisibleItemPosition() > 0) {
                        startTitleTv.visibility = View.GONE
                        searchIv.visibility = View.VISIBLE
                        midTitleTv.visibility = View.VISIBLE
                    } else {
                        startTitleTv.visibility = View.VISIBLE
                        searchIv.visibility = View.GONE
                        midTitleTv.visibility = View.GONE
                    }
                }
            })
        }
        if (savedInstanceState == null) {
            with(viewModel) {
                getAllCategories()
            }
        }
    }

    private fun observeCatalogList() {
        with(viewModel) {
            catalogsLiveData.observe(this@CatalogFragment) { catalogs ->
                renderCategories(catalogs)
            }
        }
    }

    private fun renderCategories(categories: List<Category>) {
        binding.catalogRv.adapter = adapter

        val itemList = ItemList.create().apply {
            add(searchController)
            for (item in categories) {
                add(item, categoryController)
                addAllIf(item.isSelected, item.subCategories, subCategoryController)
            }
        }

        adapter.setItems(itemList)
    }

    companion object {

        fun newInstance() =
            CatalogFragment()
    }
}