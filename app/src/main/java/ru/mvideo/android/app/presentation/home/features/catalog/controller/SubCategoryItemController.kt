package ru.mvideo.android.app.presentation.home.features.catalog.controller

import android.view.ViewGroup
import ru.mvideo.android.R
import ru.mvideo.android.data.model.category.SubCategory
import ru.mvideo.android.databinding.ItemSubcategoryBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

class SubCategoryItemController(
    private val onClick: (Long) -> Unit
) : BindableItemController<SubCategory, SubCategoryItemController.Holder>() {

    override fun getItemId(data: SubCategory): String = "$ID_TAG${data.id}"

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    inner class Holder(
        parent: ViewGroup
    ) : BindableViewHolder<SubCategory>(parent, R.layout.item_subcategory) {
        private val binding = ItemSubcategoryBinding.bind(itemView)

        override fun bind(data: SubCategory) = with(binding) {
            subCategoryNameTv.text = data.name
            itemView.setOnClickListener { onClick(data.id) }
        }
    }

    private companion object {
        const val ID_TAG = "SubCategoryItemController"
    }
}
