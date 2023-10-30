package ru.tim.recipes.domain.model

import java.io.Serializable

data class Recipe(
    val id: String = "",
    val name: String = "",
    val headline: String = "",
    val description: String = "",
    val calories: String = "",
    val carbos: String = "",
    val fats: String = "",
    val proteins: String = "",
    val difficulty: Int = 0,
    val time: String = "",
    val image: String = "",
    val thumb: String = ""
) : Serializable