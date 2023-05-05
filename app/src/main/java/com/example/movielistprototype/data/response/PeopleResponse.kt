package com.example.movielistprototype.data.response

import com.example.movielistprototype.data.model.People
import com.google.gson.annotations.SerializedName

data class PeopleResponse (

    @SerializedName("count")
    var count : Int? = null,

    @SerializedName("next")
    var next : String? = null,

    @SerializedName("previous" )
    var previous : String? = null,

    @SerializedName("results")
    var results : ArrayList<People> = arrayListOf()

)