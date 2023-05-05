package com.example.movielistprototype.data.model

import com.google.gson.annotations.SerializedName

data class People(
    @SerializedName("name")
    val name: String = " ",

    @SerializedName("height")
    val height: String = " ",

    @SerializedName("mass")
    val mass: String = " ",

    @SerializedName("gender")
    val gender: String = " ",

    @SerializedName("hair_color")
    val hairColor: String = " ",

    @SerializedName("skin_color")
    val skinColor: String = " ",

    @SerializedName("eye_color")
    val eyeColor: String = " "
)
