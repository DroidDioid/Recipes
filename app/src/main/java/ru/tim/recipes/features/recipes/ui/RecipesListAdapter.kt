package ru.tim.recipes.features.recipes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.tim.recipes.databinding.ItemListRecipeBinding
import ru.tim.recipes.domain.model.Recipe

/** Адаптер для списка рецептов. */
class RecipesListAdapter(private val callbacks: Callbacks) : RecyclerView.Adapter<RecipesListAdapter.RecipeViewHolder>() {

    private lateinit var recipes: List<Recipe>

    interface Callbacks {
        /**
         * Сообщает о нажатии на элемент списка.
         * @param recipe нажатый рецепт
         * @param imageView представление изображения
         */
        fun onItemClick(recipe: Recipe, imageView: ImageView, nameTextView: TextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListRecipeBinding.inflate(layoutInflater, parent, false)

        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount() = recipes.size

    fun updateAdapter(updatedList: List<Recipe>) {
        val result = DiffUtil.calculateDiff(RecipeDiffCallback(recipes, updatedList))
        this.recipes = updatedList
        result.dispatchUpdatesTo(this)
    }

    fun setData(recipes: List<Recipe>) {
        this.recipes = recipes
    }

    inner class RecipeViewHolder(private val binding: ItemListRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {

            binding.recipe = recipe
            binding.recipeImage.transitionName = recipe.id
            binding.nameTextView.transitionName = recipe.name

            Glide.with(itemView.context)
                .load(recipe.thumb)
                .error(
                    Glide.with(itemView.context)
                        .load(recipe.image)
                )
                .into(binding.recipeImage)

            itemView.setOnClickListener {
                callbacks.onItemClick(recipe, binding.recipeImage, binding.nameTextView)
            }
        }
    }

}