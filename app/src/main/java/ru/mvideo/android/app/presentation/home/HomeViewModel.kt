package ru.mvideo.android.app.presentation.home

import androidx.lifecycle.ViewModel
import ru.mvideo.android.app.presentation.home.router.HomeRouter
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val router: HomeRouter
) : ViewModel() {

    fun openDashboardTab() {
        router.openDashboardTab()
    }

    fun openRecommendedTab() {
        router.openRecommendedTab()
    }

    fun openReadLaterTab() {
        router.openReadLaterTab()
    }
}