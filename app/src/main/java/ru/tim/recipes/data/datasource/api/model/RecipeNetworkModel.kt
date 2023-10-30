package ru.tim.recipes.data.datasource.api.model

import com.google.gson.annotations.SerializedName

/** Модель рецепта получаемая по API.*/
data class RecipeNetworkModel(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("headline") val headline: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("calories") val calories: String? = null,
    @SerializedName("carbos") val carbos: String? = null,
    @SerializedName("fats") val fats: String? = null,
    @SerializedName("proteins") val proteins: String? = null,
    @SerializedName("difficulty") val difficulty: Int? = null,
    @SerializedName("time") val time: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("thumb") val thumb: String? = null
)