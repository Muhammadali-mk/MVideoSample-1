package ru.mvideo.android.app.global.router

import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.mvideo.android.app.presentation.home.HomeFragment
import ru.mvideo.android.app.support.base.CiceroneRouter

class GlobalRouter : CiceroneRouter() {

    fun openHomeScreen() {
        newRootScreen(Screens.Home)
    }

    private object Screens {

        object Home : SupportAppScreen() {
            override fun getFragment(): Fragment =
                HomeFragment.newInstance()
        }
    }
}