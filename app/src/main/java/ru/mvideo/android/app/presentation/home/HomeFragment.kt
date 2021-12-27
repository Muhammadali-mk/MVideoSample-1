package ru.mvideo.android.app.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.mvideo.android.R
import ru.mvideo.android.app.presentation.home.di.HomeDaggerComponent
import ru.mvideo.android.databinding.FragmentHomeBinding
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

internal class HomeFragment : Fragment(R.layout.fragment_home),
    IHasComponent<HomeDaggerComponent> {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private var supportAppNavigator: SupportAppNavigator? = null

    private lateinit var binding: FragmentHomeBinding

    override fun getComponent(): HomeDaggerComponent =
        HomeDaggerComponent.create(XInjectionManager.findComponent())

    override fun onCreate(savedInstanceState: Bundle?) {
        XInjectionManager.bindComponent(this).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        supportAppNavigator =
            SupportAppNavigator(requireActivity(), childFragmentManager, binding.frameLayout.id)

        binding.apply {
            bottomNavigationView.setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.home_dashboard -> viewModel.openDashboardTab().let { true }
                    R.id.home_catalog -> viewModel.openRecommendedTab().let { true }
                    R.id.home_test -> viewModel.openReadLaterTab().let { true }
                    else -> false
                }
            }
            bottomNavigationView.setOnItemReselectedListener {
                when (it.itemId) {
                    R.id.home_dashboard -> viewModel.openDashboardTab()
                    R.id.home_catalog -> viewModel.openRecommendedTab()
                    R.id.home_test -> viewModel.openReadLaterTab()
                }
            }
        }
        if (savedInstanceState == null)
            viewModel.openDashboardTab()
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.removeNavigator()
        supportAppNavigator?.let { navigatorHolder.setNavigator(it) }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroyView() {
        supportAppNavigator = null
        super.onDestroyView()
    }

    companion object {

        fun newInstance() = HomeFragment()
    }
}