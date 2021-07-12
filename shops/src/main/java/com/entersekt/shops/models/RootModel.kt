package com.entersekt.outlets.models

import com.google.gson.annotations.SerializedName

data class RootModel(
    @SerializedName("cities") var cities: List<City>?
)