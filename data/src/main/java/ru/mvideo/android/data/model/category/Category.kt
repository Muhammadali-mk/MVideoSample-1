package ru.mvideo.android.data.model.category

data class Category(
    val id: Long,
    val name: String,
    val logo: Int? = null,


///

//new firat message
//new second message

    val subCategories: List<SubCategory>,
    var isSelected: Boolean = false
)