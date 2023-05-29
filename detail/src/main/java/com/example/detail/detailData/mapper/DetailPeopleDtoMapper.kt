package com.example.detail.detailData.mapper

import com.example.detail.detailData.dto.DetailPeopleDto
import com.example.detail.detailData.model.DetailPeople

fun DetailPeopleDto.toPeople() : DetailPeople {
    return DetailPeople(
        name ?: "",
        height ?: "",
        mass ?: "",
        gender ?: "",
        hair_color ?: "",
        skin_color ?: "",
        eye_color ?: ""
    )
}