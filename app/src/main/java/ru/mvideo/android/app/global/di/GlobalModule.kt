package ru.mvideo.android.app.global.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.mvideo.android.app.global.GlobalViewModel
import ru.mvideo.android.app.support.viewmodel.ViewModelFactory
import ru.mvideo.android.app.support.viewmodel.ViewModelKey

@Module(includes = [GlobalModule.Binder::class])
object GlobalModule {

    @Module
    interface Binder {

        @Binds
        @GlobalScope
        fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @IntoMap
        @GlobalScope
        @ViewModelKey(GlobalViewModel::class)
        fun viewModel(viewModel: GlobalViewModel): ViewModel

    }
}