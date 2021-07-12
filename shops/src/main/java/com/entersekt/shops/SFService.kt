package com.entersekt.shops

import com.entersekt.outlets.models.City
import com.entersekt.outlets.models.Mall
import com.entersekt.outlets.models.Shop
import com.entersekt.outlets.retrofit.RetrofitHelper

class SFService(private val retrofitHelper: RetrofitHelper) {

    suspend fun getCities(): List<City>?{
        return try {
            retrofitHelper.fetchCities()?.cities
        }
        catch (ex: Exception){
            null
        }
    }

    suspend fun getCity(cityId: Int): City?{
        return try {
            val cities = retrofitHelper.fetchCities()?.cities
            cities?.first { it.id == cityId }
        }
        catch (ex: Exception){
            null
        }
    }

    suspend fun getMallsInCity(cityId: Int): List<Mall>?{
        return try {
            val cities = retrofitHelper.fetchCities()?.cities
            val city = cities?.first { it.id == cityId }
            city?.malls
        }
        catch (ex: Exception){
            null
        }
    }

    suspend fun getShopsInMall(mallId: Int): List<Shop>?{
        return try {
            val cities = retrofitHelper.fetchCities()?.cities
            var shops: List<Shop>? = ArrayList()
            cities?.forEach {
                it.malls?.forEach{ mall ->
                    if(mall.id == mallId){
                        shops = mall.shops
                    }
                }
            }
            shops
        }
        catch (ex: Exception){
            null
        }
    }

    suspend fun getShopsInCity(cityId: Int): List<Shop>?{
        return try {
            val cities = retrofitHelper.fetchCities()?.cities
            val city = cities?.first { it.id == cityId }
            val malls = city?.malls
            val shops = ArrayList<Shop>()
            malls?.forEach {
                it.shops?.forEach { shop -> shops.add(shop) }
            }
            shops
        }
        catch (ex: Exception){
            null
        }
    }

}