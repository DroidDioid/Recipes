package ru.tim.recipes.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Модель рецепта получаемая из БД.*/
@Entity(tableName = "recipes")
data class RecipeLocalModel(
    @PrimaryKey
    val id: String,
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
)