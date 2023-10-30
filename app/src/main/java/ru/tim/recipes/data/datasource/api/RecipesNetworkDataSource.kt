package ru.tim.recipes.data.datasource.api

import ru.tim.recipes.data.datasource.api.model.RecipeNetworkModel
import javax.inject.Inject

/**
 * Предоставляет методы для получения и первоначальной обработки данных с API.
 * @param recipesApiService cервис, выполняющий запросы к API.
 */
class RecipesNetworkDataSource @Inject constructor(private val recipesApiService: RecipesApiService) {

    /**
     * Возвращает результат [Result][Result] попытки получения всех рецептов.
     * В случае успеха, содержит в себе список всех рецептов полученных с API.
     * Иначе, содержит в себе исключение.
     */
    suspend fun getAllRecipes(): Result<List<RecipeNetworkModel>> {
        return try {
            val recipes = recipesApiService.getAllRecipes()
            Result.success(recipes)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }
}