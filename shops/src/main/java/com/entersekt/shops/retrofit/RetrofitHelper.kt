package com.entersekt.outlets.retrofit

import com.entersekt.outlets.models.RootModel
import retrofit2.http.*

interface RetrofitHelper {
    @GET("v2/5b7e8bc03000005c0084c210")
    suspend fun fetchCities(): RootModel?
}