package ru.mvideo.android.app.presentation.home.features.catalog.controller

import android.view.ViewGroup
import ru.mvideo.android.R
import ru.mvideo.android.databinding.ItemSearchBinding
import ru.surfstudio.android.easyadapter.controller.NoDataItemController
import ru.surfstudio.android.easyadapter.holder.BaseViewHolder

internal class SearchItemController(
    private val onFocused: () -> Unit
) : NoDataItemController<BaseViewHolder>() {

    override fun createViewHolder(parent: ViewGroup) =
        BaseViewHolder(parent, R.layout.item_search)
            .apply {
                val binding = ItemSearchBinding.bind(itemView)
                binding.searchCet.setOnFocusChangeListener { _, hasFocus ->
                    if (hasFocus) onFocused()
                }
            }
}
