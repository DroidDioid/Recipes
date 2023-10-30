package ru.tim.recipes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.tim.recipes.features.recipes.vm.RecipesListViewModel
import javax.inject.Inject
import javax.inject.Provider

/** Фабрика для генерации ViewModel-ей*/
class ViewModelFactory @Inject constructor(
    recipesListViewModelProvider: Provider<RecipesListViewModel>
) : ViewModelProvider.Factory {

    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        RecipesListViewModel::class.java to recipesListViewModelProvider
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}