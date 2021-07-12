package com.entersekt.outlets.models

import com.google.gson.annotations.SerializedName

data class Shop(
    @SerializedName("id") var id: Int?,
    @SerializedName("name") var name: String?,
)
