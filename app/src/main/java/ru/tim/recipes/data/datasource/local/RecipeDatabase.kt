package ru.tim.recipes.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.tim.recipes.data.datasource.local.model.RecipeLocalModel

/** Класс, описывающий базу данных, которая будет создана на его основании. */
@Database(entities = [RecipeLocalModel::class], version = 1, exportSchema = true)
abstract class RecipeDatabase : RoomDatabase() {

    /** Объект доступа к данным, выполняющий запросы к БД. */
    abstract val recipeDatabaseDao: RecipeDatabaseDao
}