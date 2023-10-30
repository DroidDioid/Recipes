package ru.tim.recipes.data.datasource.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val URL_BASE = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"

@RunWith(AndroidJUnit4::class)
class RecipesNetworkDataSourceTest {

    private lateinit var recipesApiService: RecipesApiService
    private lateinit var retrofit: Retrofit

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createAPI() {
        retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        recipesApiService = retrofit.create(RecipesApiService::class.java)
    }

    @Test
    fun testGetFromAPI() {
        runBlocking {
            val dbRecipes = recipesApiService.getAllRecipes()
            val count = dbRecipes.count()
            assertTrue(count > 0)
        }
    }
}