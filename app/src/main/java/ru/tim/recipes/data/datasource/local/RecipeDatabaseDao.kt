package ru.tim.recipes.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import ru.tim.recipes.data.datasource.local.model.RecipeLocalModel

/** Интерфейс реализуемый [Room][androidx.room], предоставляет доступ к базе данных. */
@Dao
interface RecipeDatabaseDao {

    /** Добавляет рецепты в базу данных. Если они там уже есть, то заменяет старые на новые.*/
    @Insert(onConflict = REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeLocalModel>)

    /** Получает и возвращает список всех рецептов из базы данных, отсортированных по ID. */
    @Query("SELECT * FROM recipes ORDER BY id DESC")
    suspend fun getAllRecipes(): List<RecipeLocalModel>

}