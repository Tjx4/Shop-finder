package com.entersekt.outlets.models

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("id") var id: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("malls") var malls: List<Mall>?
)
