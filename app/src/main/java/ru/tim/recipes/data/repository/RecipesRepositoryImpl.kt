package ru.tim.recipes.data.repository

import ru.tim.recipes.data.datasource.api.RecipesNetworkDataSource
import ru.tim.recipes.data.datasource.local.RecipesLocalDataSource
import ru.tim.recipes.data.repository.mapper.ApiToEntityMapper
import ru.tim.recipes.data.repository.mapper.EntityToLocalMapper
import ru.tim.recipes.data.repository.mapper.LocalToEntityMapper
import ru.tim.recipes.domain.RecipesRepository
import ru.tim.recipes.domain.model.Recipe

/**
 * Реализация интерфеса репозитория, определенного в слое [domain][ru.tim.recipes.domain].
 * Предоставляет методы для получения данных
 * на основании источников данных [recipesNetworkDataSource] и [recipeLocalDataSource].
 */
class RecipesRepositoryImpl(
    private val recipesNetworkDataSource: RecipesNetworkDataSource,
    private val recipeLocalDataSource: RecipesLocalDataSource,
) : RecipesRepository {

    /**
     * Возвращает результат [Result][Result] попытки получения всех рецептов.
     * Сначала пробует получить данные по API, в случае неудачи получает данные из БД.
     * В случае успеха, содержит в себе список всех рецептов полученных из API или БД.
     * Иначе, содержит в себе исключение.
     */
    override suspend fun getAllRecipes(): Result<List<Recipe>> {

        recipesNetworkDataSource.getAllRecipes().let { resultNetworkRecipesResponse ->
            if (resultNetworkRecipesResponse.isSuccess) {
                resultNetworkRecipesResponse.getOrNull()!!.let {
                    return Result.success(ApiToEntityMapper.map(it))
                }
            } else {

                recipeLocalDataSource.getAllRecipes().let { resultLocalRecipesResponse ->
                    if (resultLocalRecipesResponse.isSuccess) {
                        resultLocalRecipesResponse.getOrNull()!!.let {
                            return Result.success(LocalToEntityMapper.map(it))
                        }
                    }
                }

                return Result.failure(resultNetworkRecipesResponse.exceptionOrNull()!!)

            }
        }

    }

    /** Сохраняет список рецептов [recipes] в базу данных. */
    override suspend fun saveAllRecipes(recipes: List<Recipe>) {
        recipeLocalDataSource.insertAllRecipes(EntityToLocalMapper.map(recipes))
    }

}