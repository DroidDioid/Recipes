package ru.tim.recipes.data.datasource.local

import ru.tim.recipes.data.datasource.local.model.RecipeLocalModel
import ru.tim.recipes.domain.model.Recipe
import javax.inject.Inject

/**
 * Предоставляет методы для получения и первоначальной обработки данных с БД.
 * @param recipeDatabaseDao объект доступа к данным, выполняющий запросы к БД.
 */
class RecipesLocalDataSource @Inject constructor(private val recipeDatabaseDao: RecipeDatabaseDao) {

    /**
     * Возвращает результат [Result][Result] попытки получения всех рецептов.
     * В случае успеха, содержит в себе список всех рецептов полученных с БД.
     * Иначе, содержит в себе исключение.
     */
    suspend fun getAllRecipes(): Result<List<RecipeLocalModel>> {
        return try {
            val recipes = recipeDatabaseDao.getAllRecipes()
            Result.success(recipes)
        } catch (exception: Exception) {
            Result.failure(exception)
        }
    }

    /**
     * Добавляет список переданных рецептов [recipes] в базу данных.
     * Если они там уже есть, то заменяет старые на новые.
     */
    suspend fun insertAllRecipes(recipes: List<RecipeLocalModel>) {
        try {
            recipeDatabaseDao.insertRecipes(recipes)
        } catch (exception: Exception) {

        }
    }

}