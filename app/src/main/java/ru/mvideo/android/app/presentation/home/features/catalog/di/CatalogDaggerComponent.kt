package ru.mvideo.android.app.presentation.home.features.catalog.di

import dagger.Component
import ru.mvideo.android.app.global.di.GlobalComponent
import ru.mvideo.android.app.presentation.home.features.catalog.CatalogFragment

@CatalogDaggerScope
@Component(
    dependencies = [GlobalComponent::class],
    modules = [CatalogDaggerModule::class]
)
internal interface CatalogDaggerComponent {

    fun inject(fragment: CatalogFragment)

    @Component.Factory
    interface Factory {
        fun create(component: GlobalComponent): CatalogDaggerComponent
    }

    companion object {
        fun create(component: GlobalComponent): CatalogDaggerComponent =
            DaggerCatalogDaggerComponent
                .factory()
                .create(component)
    }
}