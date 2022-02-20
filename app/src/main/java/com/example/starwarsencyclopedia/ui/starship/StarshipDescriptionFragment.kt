package com.example.starwarsencyclopedia.ui.starship

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.starwarsencyclopedia.databinding.FragmentCharacterDescriptionBinding
import com.example.starwarsencyclopedia.databinding.FragmentStarshipDescriptionBinding
import com.example.starwarsencyclopedia.viewmodel.CharacterViewModel
import com.example.starwarsencyclopedia.viewmodel.StarshipViewModel

class StarshipDescriptionFragment : Fragment() {

    val viewModel : StarshipViewModel by viewModels()

    val characterViewModel: CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentStarshipDescriptionBinding.inflate(inflater)

        viewModel.getStarship(characterViewModel.currentIds.value!!["starship"]!!)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}