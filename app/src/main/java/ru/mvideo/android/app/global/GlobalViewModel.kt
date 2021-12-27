package ru.mvideo.android.app.global

import androidx.lifecycle.ViewModel
import ru.mvideo.android.app.global.router.GlobalRouter
import javax.inject.Inject

class GlobalViewModel @Inject constructor(
    private val router: GlobalRouter
) : ViewModel() {

    fun onActivityCreate() {
        router.openHomeScreen()
    }
}