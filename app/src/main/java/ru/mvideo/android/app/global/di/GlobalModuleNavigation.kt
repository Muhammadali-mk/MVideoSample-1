package ru.mvideo.android.app.global.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.mvideo.android.app.global.router.GlobalRouter
import ru.mvideo.android.app.presentation.home.router.HomeRouter

@Module(
    includes = [
        GlobalModuleNavigation.GlobalRouterProviders::class,
        GlobalModuleNavigation.HomerRouterProviders::class
    ]
)
object GlobalModuleNavigation {

    @Module
    object GlobalRouterProviders {

        private val globalCicerone: Cicerone<GlobalRouter> = Cicerone.create(GlobalRouter())

        @JvmStatic
        @Provides
        @GlobalScope
        fun globalRouter(): GlobalRouter = globalCicerone.router

        @JvmStatic
        @Provides
        fun globalCicerone(): Cicerone<GlobalRouter> = globalCicerone
    }

    @Module
    object HomerRouterProviders {

        @JvmStatic
        @Provides
        @GlobalScope
        fun homerRouter(): HomeRouter = HomeRouter()
    }
}