package ru.mvideo.android.app.presentation.home.di

import dagger.Component
import ru.mvideo.android.app.global.di.GlobalComponent
import ru.mvideo.android.app.presentation.home.HomeFragment
import ru.mvideo.android.data.repository.RepositoryProvider

@HomeDaggerScope
@Component(
    dependencies = [GlobalComponent::class],
    modules = [HomeDaggerModule::class]
)
internal interface HomeDaggerComponent : RepositoryProvider {

    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(component: GlobalComponent): HomeDaggerComponent
    }

    companion object {
        fun create(component: GlobalComponent): HomeDaggerComponent =
            DaggerHomeDaggerComponent
                .factory()
                .create(component)
    }
}