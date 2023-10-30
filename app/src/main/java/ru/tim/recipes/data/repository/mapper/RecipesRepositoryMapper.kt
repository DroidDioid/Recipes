/** Файл содержит конвертеры для преобразования моделей между собой (API, БД и обычная модели)*/

package ru.tim.recipes.data.repository.mapper

import ru.tim.recipes.core.BaseMapper
import ru.tim.recipes.data.datasource.api.model.RecipeNetworkModel
import ru.tim.recipes.data.datasource.local.model.RecipeLocalModel
import ru.tim.recipes.domain.model.Recipe

/** Объект для конвертации списка API-моделей рецепта в список моделей рецепта данного приложения.*/
object ApiToEntityMapper : BaseMapper<List<RecipeNetworkModel>, List<Recipe>> {
    override fun map(type: List<RecipeNetworkModel>?): List<Recipe> {
        return type?.map {
                Recipe(
                    id = it.id ?: "",
                    name = it.name ?: "",
                    headline = it.headline ?: "",
                    description = it.description ?: "",
                    calories = it.calories ?: "",
                    carbos = it.carbos ?: "",
                    fats = it.fats ?: "",
                    proteins = it.proteins ?: "",
                    difficulty = it.difficulty ?: -1,
                    time = it.time ?: "",
                    thumb = it.thumb ?: "",
                    image = it.image ?: ""
                )
            } ?: listOf()
    }
}

/** Объект для конвертации списка моделей рецепта данного приложения в список БД-моделей рецепта.*/
object EntityToLocalMapper : BaseMapper<List<Recipe>, List<RecipeLocalModel>> {
    override fun map(type: List<Recipe>?): List<RecipeLocalModel> {
        return type?.map {
            RecipeLocalModel(
                id = it.id,
                name = it.name,
                headline = it.headline,
                description = it.description,
                calories = it.calories,
                carbos = it.carbos,
                fats = it.fats,
                proteins = it.proteins,
                difficulty = it.difficulty,
                time = it.time,
                thumb = it.thumb,
                image = it.image
            )
        } ?: listOf()
    }
}

/** Объект для конвертации списка БД-моделей рецепта в список моделей рецепта данного приложения.*/
object LocalToEntityMapper : BaseMapper<List<RecipeLocalModel>, List<Recipe>> {
    override fun map(type: List<RecipeLocalModel>?): List<Recipe> {
        return type?.map {
                Recipe(
                    id = it.id,
                    name = it.name,
                    headline = it.headline,
                    description = it.description,
                    calories = it.calories,
                    carbos = it.carbos,
                    fats = it.fats,
                    proteins = it.proteins,
                    difficulty = it.difficulty,
                    time = it.time,
                    thumb = it.thumb,
                    image = it.image
                )
            } ?: listOf()
    }
}

/** Объект для конвертации списка API-моделей рецепта в список БД-моделей рецепта.*/
object ApiToLocalModelMapper : BaseMapper<List<RecipeNetworkModel>, List<RecipeLocalModel>> {
    override fun map(type: List<RecipeNetworkModel>?): List<RecipeLocalModel> {
        return type?.map {
            RecipeLocalModel(
                id = it.id ?: "",
                name = it.name ?: "",
                headline = it.headline ?: "",
                description = it.description ?: "",
                calories = it.calories ?: "",
                carbos = it.carbos ?: "",
                fats = it.fats ?: "",
                proteins = it.proteins ?: "",
                difficulty = it.difficulty ?: -1,
                time = it.time ?: "",
                thumb = it.thumb ?: "",
                image = it.image ?: ""
            )
        } ?: listOf()
    }
}