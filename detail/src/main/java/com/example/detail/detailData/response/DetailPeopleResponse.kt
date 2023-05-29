package com.example.detail.detailData.response

import com.example.detail.detailData.dto.DetailPeopleDto
import com.google.gson.annotations.SerializedName

data class DetailPeopleResponse (

    @SerializedName("count")
    var count : Int? = null,

    @SerializedName("next")
    var next : String? = null,

    @SerializedName("previous" )
    var previous : String? = null,

    @SerializedName("results")
    var results : ArrayList<DetailPeopleDto> = arrayListOf()

)