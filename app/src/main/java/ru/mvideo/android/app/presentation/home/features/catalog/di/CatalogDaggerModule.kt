package ru.mvideo.android.app.presentation.home.features.catalog.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.mvideo.android.app.presentation.home.features.catalog.CatalogViewModel
import ru.mvideo.android.app.support.viewmodel.ViewModelFactory
import ru.mvideo.android.app.support.viewmodel.ViewModelKey

@Module(includes = [CatalogDaggerModule.Binder::class])
internal object CatalogDaggerModule {

    @Module
    interface Binder {

        @Binds
        @CatalogDaggerScope
        fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @IntoMap
        @CatalogDaggerScope
        @ViewModelKey(CatalogViewModel::class)
        fun viewModel(viewModel: CatalogViewModel): ViewModel

    }
}