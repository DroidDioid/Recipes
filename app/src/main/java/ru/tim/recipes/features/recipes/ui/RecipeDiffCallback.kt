package ru.tim.recipes.features.recipes.ui

import androidx.recyclerview.widget.DiffUtil
import ru.tim.recipes.domain.model.Recipe

/** Класс для вычисления различий между старым и новым списками рецептов
 * для правильной конвертации одного списка в другой. */
class RecipeDiffCallback(
    private val oldRecipes: List<Recipe>,
    private val newRecipes: List<Recipe>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldRecipes.size

    override fun getNewListSize(): Int = newRecipes.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRecipes[oldItemPosition].id == newRecipes[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldRecipes[oldItemPosition] == newRecipes[newItemPosition]
    }
}