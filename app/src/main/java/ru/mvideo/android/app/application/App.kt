package ru.mvideo.android.app.application

import android.app.Application
import android.content.Context
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.mvideo.android.app.application.di.AppComponent
import kotlin.properties.Delegates

class App : Application(),
    IHasComponent<AppComponent> {

    private var pureContext: Context by Delegates.notNull()

    override fun getComponent(): AppComponent =
        AppComponent.create(pureContext)

    override fun attachBaseContext(base: Context) {
        pureContext = base
        XInjectionManager.let { it.init(this); it.bindComponent(this).inject(this) }
        super.attachBaseContext(base)
    }
}