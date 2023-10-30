package ru.tim.recipes.data.datasource.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tim.recipes.data.datasource.local.model.RecipeLocalModel
import ru.tim.recipes.domain.model.Recipe
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class RecipeDatabaseDaoTest {

    private lateinit var recipeDatabaseDao: RecipeDatabaseDao
    private lateinit var database: RecipeDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, RecipeDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        recipeDatabaseDao = database.recipeDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun testInsertIntoAndGetFromDatabase() {
        runBlocking {
            val recipes = listOf(RecipeLocalModel(id = "123"), RecipeLocalModel(id = "456"))
            recipeDatabaseDao.insertRecipes(recipes)
            val dbRecipes = recipeDatabaseDao.getAllRecipes()
            val count = dbRecipes.count()
            assertEquals(2, count)
            assertEquals(dbRecipes.toSet(), recipes.toSet())
        }
    }

}