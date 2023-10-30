package ru.tim.recipes.features.recipes.ui

import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.tim.recipes.databinding.FragmentRecipesListBinding
import ru.tim.recipes.domain.model.Recipe
import ru.tim.recipes.features.recipes.vm.RecipesListViewModel

/**
 * Создаёт и отображает экран со списком рецептов.
 * Реализует интерфейс [RecipesListAdapter.Callbacks] для обработки нажатий
 * и получения данных из адаптера списка.
 */
@AndroidEntryPoint
class RecipesListFragment : Fragment(), RecipesListAdapter.Callbacks {

    private val recipesListViewModel: RecipesListViewModel by viewModels()
    private lateinit var binding: FragmentRecipesListBinding
    private val recipesListAdapter = RecipesListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecipesListBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = recipesListViewModel

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipesListViewModel.recipesList.observe(viewLifecycleOwner) {
            recipesListAdapter.updateAdapter(it)
        }
    }

    /**
     * Настраивает RecyclerView, задавая ему layoutManager и adapter.
     */
    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        recipesListAdapter.setData(emptyList())
        binding.recyclerView.adapter = recipesListAdapter
    }

    /**
     * Запускает экран детализации рецепта при нажатии на элемент списка.
     * @param recipe нажатый рецепт
     * @param imageView представление изображения
     */
    override fun onItemClick(recipe: Recipe, imageView: ImageView, nameTextView: TextView) {
        val action = RecipesListFragmentDirections.actionListToDetail(recipe)
        val extras = FragmentNavigatorExtras(imageView to recipe.id, nameTextView to recipe.name)
        findNavController().navigate(action, extras)
    }


}