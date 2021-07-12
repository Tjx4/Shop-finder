package com.entersekt.outlets.models

import com.google.gson.annotations.SerializedName

data class Mall(
    @SerializedName("id") var id: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("shops") var shops: List<Shop>?
)