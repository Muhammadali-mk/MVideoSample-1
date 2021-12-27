package ru.mvideo.android.app.presentation.home.router

import androidx.fragment.app.Fragment
import ru.mvideo.android.app.presentation.home.features.catalog.CatalogFragment
import ru.mvideo.android.app.support.base.FeatureRouter
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppScreen

class HomeRouter : FeatureRouter() {

    private var bottomNavigationRouter: Router? = null
    private var navigationListener: ((tab: HomeTab) -> Unit)? = null

    fun setRouter(router: Router) {
        this.bottomNavigationRouter = router
    }

    fun setNavigationListener(listener: (tab: HomeTab) -> Unit) {
        navigationListener = listener
    }

    fun openDashboardTab(isNotifyRequired: Boolean = false) {
        bottomNavigationRouter?.replaceScreen(Tabs.DashboardTab)
        if (isNotifyRequired)
            navigationListener?.invoke(HomeTab.Dashboard)
    }

    fun openRecommendedTab(isNotifyRequired: Boolean = false) {
        bottomNavigationRouter?.replaceScreen(Tabs.Catalog)
        if (isNotifyRequired)
            navigationListener?.invoke(HomeTab.Catalog)
    }

    fun openReadLaterTab(isNotifyRequired: Boolean = false) {
        bottomNavigationRouter?.replaceScreen(Tabs.Test)
        if (isNotifyRequired)
            navigationListener?.invoke(HomeTab.Test)
    }

    private object Tabs {

        object DashboardTab : SupportAppScreen() {
            override fun getFragment(): Fragment =
                CatalogFragment.newInstance()
        }

        object Catalog : SupportAppScreen() {
            override fun getFragment(): Fragment =
                CatalogFragment.newInstance()
        }

        object Test : SupportAppScreen() {
            override fun getFragment(): Fragment =
                CatalogFragment.newInstance()
        }
    }

    sealed class HomeTab {
        object Dashboard : HomeTab()
        object Catalog : HomeTab()
        object Test : HomeTab()
    }
}