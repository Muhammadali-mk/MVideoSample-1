package ru.mvideo.android.app.global.di

import dagger.Component
import ru.mvideo.android.app.application.di.AppComponent
import ru.mvideo.android.app.global.GlobalActivity
import ru.mvideo.android.app.global.router.GlobalRouter
import ru.mvideo.android.app.presentation.home.router.HomeRouter
import ru.mvideo.android.data.repository.RepositoryProvider

@GlobalScope
@Component(
    dependencies = [AppComponent::class],
    modules = [
        GlobalModule::class,
        GlobalModuleNavigation::class
    ]
)
interface GlobalComponent : RepositoryProvider {

    val globalRouter: GlobalRouter

    val homeRouter: HomeRouter

    fun inject(activity: GlobalActivity)

    @Component.Factory
    interface Factory {
        fun create(component: AppComponent): GlobalComponent
    }

    companion object {
        fun create(component: AppComponent): GlobalComponent =
            DaggerGlobalComponent
                .factory()
                .create(component)
    }
}