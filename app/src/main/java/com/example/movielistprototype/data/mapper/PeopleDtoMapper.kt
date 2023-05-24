package com.example.movielistprototype.data.mapper

import com.example.movielistprototype.data.dto.PeopleDto
import com.example.movielistprototype.data.model.People

fun PeopleDto.toPeople() : People {
    return People(
        name,
        height,
        mass,
        gender,
        hairColor,
        skinColor,
        eyeColor
    )
}