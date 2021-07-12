package com.entersekt.shopfinder.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.entersekt.outlets.models.City
import com.entersekt.shopfinder.R
import com.entersekt.shopfinder.base.fragments.BaseFragment
import com.entersekt.shopfinder.adapters.CitiesAdapter
import com.entersekt.shopfinder.databinding.FragmentDashboardBinding
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment(), CitiesAdapter.CityClickListener {
    private lateinit var binding: FragmentDashboardBinding
    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        myDrawerController.setTitle(getString(R.string.app_name))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false)
        binding.lifecycleOwner = this
        binding.dashboardViewModel = dashboardViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Navigation.findNavController(view).currentDestination?.label = getString(R.string.app_name)
        addObservers()

        btnRetry.setOnClickListener {
            dashboardViewModel.showLoadingAndGetCities()
        }
    }

    private fun addObservers() {
        dashboardViewModel.isLoading.observe(viewLifecycleOwner, { onLoading(it)})
        dashboardViewModel.cities.observe(viewLifecycleOwner, { onCitiesSet(it)})
        dashboardViewModel.error.observe(viewLifecycleOwner, { onError(it)})
    }

    private fun onLoading(isLoading: Boolean) {
        llError.visibility = View.GONE
        loader.visibility = View.VISIBLE
        rvCities.visibility = View.GONE
    }

    private fun onError(errorMessage: String) {
        llError.visibility = View.VISIBLE
        loader.visibility = View.GONE
        rvCities.visibility = View.GONE
    }


    private fun onCitiesSet(cities: List<City>) {
        val citiesLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvCities?.layoutManager = citiesLayoutManager
        var citiesAdapter = CitiesAdapter(requireContext(), cities)
        citiesAdapter.setCityClickListener(this)
        rvCities?.adapter = citiesAdapter

        llError.visibility = View.GONE
        loader.visibility = View.GONE
        rvCities.visibility = View.VISIBLE
    }

    override fun onCityClickListener(view: View, position: Int) {
        val city = dashboardViewModel.cities.value?.get(position)
    }

}