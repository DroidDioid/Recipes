package ru.tim.recipes.data.datasource.api

import retrofit2.http.GET
import ru.tim.recipes.data.datasource.api.model.RecipeNetworkModel

/** Интерфейс реализуемый Retrofit, предоставляет доступ к
 * [API](https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/recipes.json). */
interface RecipesApiService {

    /**
     * Возвращает список всех рецептов полученных с API.
     */
    @GET("recipes.json")
    suspend fun getAllRecipes(): List<RecipeNetworkModel>
}