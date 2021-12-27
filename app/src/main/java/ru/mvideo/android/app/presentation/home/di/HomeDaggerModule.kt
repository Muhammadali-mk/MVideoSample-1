package ru.mvideo.android.app.presentation.home.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.mvideo.android.app.presentation.home.HomeViewModel
import ru.mvideo.android.app.presentation.home.router.HomeRouter
import ru.mvideo.android.app.support.viewmodel.ViewModelFactory
import ru.mvideo.android.app.support.viewmodel.ViewModelKey

@Module(includes = [HomeDaggerModule.Binder::class, HomeDaggerModule.Provider::class])
internal object HomeDaggerModule {

    @Module
    interface Binder {

        @Binds
        @HomeDaggerScope
        fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @IntoMap
        @HomeDaggerScope
        @ViewModelKey(HomeViewModel::class)
        fun viewModel(viewModel: HomeViewModel): ViewModel

    }

    @Module
    object Provider {

        private val cicerone: Cicerone<Router> = Cicerone.create()

        @JvmStatic
        @Provides
        fun navigatorHolder(
            homeRouter: HomeRouter,
        ): NavigatorHolder {
            homeRouter.setRouter(cicerone.router)
            return cicerone.navigatorHolder
        }
    }
}