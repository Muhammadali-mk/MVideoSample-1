package ru.mvideo.android.app.application.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.mvideo.android.app.application.App
import ru.mvideo.android.data.repository.RepositoryProvider
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, AppRepository::class])
interface AppComponent : RepositoryProvider {

    fun inject(app: App)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    companion object {
        fun create(context: Context): AppComponent =
            DaggerAppComponent
                .factory()
                .create(context)
    }
}