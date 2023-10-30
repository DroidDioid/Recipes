package ru.tim.recipes.features.recipes.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import ru.tim.recipes.domain.RecipesRepository
import ru.tim.recipes.domain.model.Recipe
import ru.tim.recipes.utils.MainDispatcherRule

@OptIn(ExperimentalCoroutinesApi::class)
class RecipesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var repository: RecipesRepository
    private lateinit var recipesListViewModel: RecipesListViewModel

    @Before
    fun setUp() {
        repository = mock(RecipesRepository::class.java)
        recipesListViewModel = RecipesListViewModel(repository)
    }

    @Test
    fun testRepositoryGetRecipesCall() {
        runBlocking {
            verify(repository).getAllRecipes()
        }
    }

    @Test
    fun testDataIsNotLoadingAndPlaceholderIsHiddenAndRecipesListIsNotEmpty() = runTest {
        val recipesList = listOf(Recipe(id = "testId"))
        val result = Result.success(recipesList)
        Mockito.`when`(repository.getAllRecipes())
            .thenReturn(result)

        recipesListViewModel.loadRecipes()

        assertNotNull(recipesListViewModel.isPlaceholderVisible.value)
        assertFalse(recipesListViewModel.isPlaceholderVisible.value!!)
        assertNotNull(recipesListViewModel.isLoading.value)
        assertFalse(recipesListViewModel.isLoading.value!!)
        assertNotNull(recipesListViewModel.recipesList.value)
        assertEquals(recipesListViewModel.recipesList.value, recipesList)

        verify(repository).saveAllRecipes(recipesList)
    }

    @Test
    fun testDataIsNotLoadingAndPlaceholderIsVisible() = runTest {
        val result = Result.failure<List<Recipe>>(Exception())
        Mockito.`when`(repository.getAllRecipes())
            .thenReturn(result)

        recipesListViewModel.loadRecipes()

        assertNotNull(recipesListViewModel.isPlaceholderVisible.value)
        assertTrue(recipesListViewModel.isPlaceholderVisible.value!!)
        assertNotNull(recipesListViewModel.isLoading.value)
        assertFalse(recipesListViewModel.isLoading.value!!)
    }
}