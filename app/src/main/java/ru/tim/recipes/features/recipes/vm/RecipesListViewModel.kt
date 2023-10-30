package ru.tim.recipes.features.recipes.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.tim.recipes.data.repository.RecipesRepositoryImpl
import ru.tim.recipes.domain.RecipesRepository
import ru.tim.recipes.domain.model.Recipe
import javax.inject.Inject

/**
 * ViewModel для фрагмента
 * [RecipesListFragment][ru.tim.recipes.features.recipes.ui.RecipesListFragment].
 */
@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    /** Список рецептов. */
    val recipesList: LiveData<List<Recipe>>
        get() = _recipesList
    private val _recipesList = MutableLiveData(emptyList<Recipe>())

    /** Сообщает загружаются ли в данный момент данные. */
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val _isLoading = MutableLiveData(false)

    /** Определяет видимость заменителя списка. */
    val isPlaceholderVisible: LiveData<Boolean>
        get() = _isPlaceholderVisible
    private val _isPlaceholderVisible = MutableLiveData(false)

    init {
        loadRecipes()
    }

    /** Загружает рецепты, сохраняет их в БД и управляет видимостью элементов на экране. */
    fun loadRecipes() {
        viewModelScope.launch {
            _isLoading.value = true
            _isPlaceholderVisible.value = false

            val result = recipesRepository.getAllRecipes()
            _isLoading.value = false

            if (result.isSuccess) {
                result.getOrNull()?.let {
                    _recipesList.value = it
                    recipesRepository.saveAllRecipes(it)
                }
            }

            _isPlaceholderVisible.value = (result.isFailure || _recipesList.value.isNullOrEmpty())
        }
    }

}