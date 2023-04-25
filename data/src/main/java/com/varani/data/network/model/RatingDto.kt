package com.varani.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Ana Varani on 25/04/2023.
 */
data class RatingDto(

    @SerializedName("Value")
    var value: String? = null,

    @SerializedName("Source")
    var source: String? = null
)
