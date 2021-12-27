package ru.mvideo.android.app.global

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.mvideo.android.app.global.di.GlobalComponent
import ru.mvideo.android.app.global.router.GlobalRouter
import ru.mvideo.android.databinding.ActivityGlobalBinding
import javax.inject.Inject

class GlobalActivity : AppCompatActivity(), IHasComponent<GlobalComponent> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: GlobalViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var cicerone: Cicerone<GlobalRouter>
    private val navigatorHolder: NavigatorHolder by lazy { cicerone.navigatorHolder }
    private val navigator by lazy { SupportAppNavigator(this, binding.frameLayout.id) }

    private val binding by lazy { ActivityGlobalBinding.inflate(layoutInflater) }

    override fun getComponent(): GlobalComponent =
        GlobalComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.WHITE
        viewModel.onActivityCreate()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}