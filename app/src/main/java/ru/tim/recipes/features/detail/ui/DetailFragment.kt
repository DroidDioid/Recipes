package ru.tim.recipes.features.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import ru.tim.recipes.databinding.FragmentDetailBinding
import ru.tim.recipes.databinding.FragmentRecipesListBinding
import ru.tim.recipes.features.detail.vm.DetailViewModel
import ru.tim.recipes.domain.model.Recipe
import ru.tim.recipes.features.recipes.ui.RecipesListAdapter
import ru.tim.recipes.features.recipes.vm.RecipesListViewModel

/** Создаёт и отображает экран детального просмотра рецепта. */
@AndroidEntryPoint
class DetailFragment : Fragment() {

    //private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        //binding.viewModel = detailViewModel
        val recipe = args.recipe as Recipe
        binding.recipe = recipe

        Glide.with(requireContext())
            .load(recipe.image)
            .thumbnail(
                Glide.with(requireContext())
                    .load(recipe.thumb)
            )
            .into(binding.recipeImage)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipe = args.recipe as Recipe
        binding.recipeImage.transitionName = recipe.id
        binding.nameTextView.transitionName = recipe.name

        (requireActivity() as AppCompatActivity).supportActionBar?.title = recipe.name
    }
}