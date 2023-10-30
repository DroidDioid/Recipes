package ru.tim.recipes.domain

import ru.tim.recipes.domain.model.Recipe
import javax.inject.Singleton

@Singleton
interface RecipesRepository {

    suspend fun getAllRecipes(): Result<List<Recipe>>
    suspend fun saveAllRecipes(recipes: List<Recipe>)

}