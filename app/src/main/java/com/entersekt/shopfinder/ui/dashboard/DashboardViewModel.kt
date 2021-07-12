package com.entersekt.shopfinder.ui.dashboard

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.entersekt.outlets.models.City
import com.entersekt.shopfinder.R
import com.entersekt.shops.SFService
import com.platform45.fx45.base.viewmodel.BaseVieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel(application: Application, private val sfService: SFService) : BaseVieModel(application) {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean>
        get() = _isLoading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: MutableLiveData<String>
        get() = _error

    private val _cities: MutableLiveData<List<City>> = MutableLiveData()
    val cities: MutableLiveData<List<City>>
        get() = _cities

    init {
        showLoadingAndGetCities()
    }

    fun showLoadingAndGetCities(){
        _isLoading.value = true
        showCities()
    }

    fun showCities(){
        viewModelScope.launch(Dispatchers.IO) {
            var cities = sfService.getCities()
            withContext(Dispatchers.Main) {
                when {
                    cities.isNullOrEmpty() -> showCachedCities()
                    else -> cacheAndSetCities(cities)
                }
            }
        }
    }

    fun cacheAndSetCities(cities: List<City>) {
        _cities.value = cities
        sfService.cacheData(app, cities)
    }

    fun showCachedCities() {
        val cachedCities = sfService.getCacheData(app)
        if (cachedCities.isNullOrEmpty()) {
            _error.value = app.getString(R.string.cities_error)
        } else {
            _cities.value = cachedCities
        }
    }

}