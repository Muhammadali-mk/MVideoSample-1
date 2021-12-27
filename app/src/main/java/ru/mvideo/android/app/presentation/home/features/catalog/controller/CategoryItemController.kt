package ru.mvideo.android.app.presentation.home.features.catalog.controller

import android.view.ViewGroup
import ru.mvideo.android.R
import ru.mvideo.android.data.model.category.Category
import ru.mvideo.android.databinding.ItemCategoryBinding
import ru.surfstudio.android.easyadapter.controller.BindableItemController
import ru.surfstudio.android.easyadapter.holder.BindableViewHolder

internal class CategoryItemController(
    private val onClick: (Long, Boolean) -> Unit
) : BindableItemController<Category, CategoryItemController.Holder>() {

    override fun getItemId(data: Category): String = "$ID_TAG${data.id}"

    override fun createViewHolder(parent: ViewGroup): Holder = Holder(parent)

    inner class Holder(
        parent: ViewGroup
    ) : BindableViewHolder<Category>(parent, R.layout.item_category) {
        private val binding = ItemCategoryBinding.bind(itemView)

        override fun bind(data: Category) = with(binding) {
            nameTv.text = data.name
            itemView.setOnClickListener { onClick(data.id, !data.isSelected) }
            val endIcon =
                if (data.isSelected) R.drawable.ic_arrow_top else R.drawable.ic_arrow_down
            detailsIv.setImageResource(endIcon)

        }
    }

    private companion object {
        const val ID_TAG = "CategoryItemController"
    }
}
