package ru.mvideo.android.data.repository.catalog

import io.reactivex.Single
import ru.mvideo.android.data.model.category.Category
import ru.mvideo.android.data.model.category.SubCategory

internal class CatalogRepositoryImpl : CatalogRepository {
    //TODO in order to test taken fake data
    /**
    @param all params should be written in order to understand other developers
     **/
    override fun getCategories(): Single<List<Category>> {
        return Single.create { emitter ->
            emitter.onSuccess(
                listOf(
                    Category(
                        1,
                        "Телевизор и цифровое ТВ ",
                        0,
                        subCategories = listOf(
                            SubCategory(11, "Телевизоры"),
                            SubCategory(11, "Спутниковое/цифровое ТВ"),
                            SubCategory(11, "Приставки Apple TV"),
                            SubCategory(11, "Проектор и экраны"),
                            SubCategory(11, "DVD, Blue-Ray и медиаплееры "),
                            SubCategory(11, "Акксессуары"),
                        )
                    ),
                    Category(
                        2,
                        "Ноутбуки компьютеры и периферия ",
                        0,
                        subCategories = listOf(
                            SubCategory(22, "Ноутбуки"),
                            SubCategory(22, "Системные блоки"),
                            SubCategory(22, "Моноблоки"),
                            SubCategory(22, "Мониторы и аксессуары "),
                            SubCategory(22, "Компьютерные комплектующие "),
                            SubCategory(22, "Огротехника и офисные оборудование"),
                            SubCategory(22, "Роутер и сетевое  оборудование"),
                            SubCategory(22, "Акксессуары"),
                        )
                    ),
                    Category(
                        3,
                        "Смартфон и гаджеты",
                        0,
                        subCategories = listOf(
                            SubCategory(33, "Смартфон и мобильные телфоны"),
                            SubCategory(33, "Apple"),
                            SubCategory(33, "Samsung"),
                            SubCategory(33, "Смарт часы и гаджеты"),
                            SubCategory(33, "Планшеты и электронные книги"),
                            SubCategory(33, "Умный дом"),
                            SubCategory(33, "Аксессуар для смартфонов "),
                        )
                    ),
                    Category(
                        4,
                        "Фото и видео ",
                        0,
                        subCategories = listOf(
                            SubCategory(44, "Фотоаппараты"),
                            SubCategory(44, "Объективы, выспышки и оптика"),
                            SubCategory(44, "Экшн-камеры"),
                            SubCategory(44, "Видео камеры "),
                            SubCategory(44, "Фотоприонтеры и шифровое фоторамка "),
                            SubCategory(44, "Акксессуары"),
                        )
                    )
                )
            )
        }

    }
}