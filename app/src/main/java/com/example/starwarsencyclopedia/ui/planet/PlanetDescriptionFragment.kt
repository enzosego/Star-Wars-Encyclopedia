package com.example.starwarsencyclopedia.ui.planet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.starwarsencyclopedia.databinding.FragmentCharacterDescriptionBinding
import com.example.starwarsencyclopedia.databinding.FragmentPlanetDescriptionBinding
import com.example.starwarsencyclopedia.viewmodel.CharacterViewModel
import com.example.starwarsencyclopedia.viewmodel.PlanetViewModel

class PlanetDescriptionFragment : Fragment() {

    val viewModel: PlanetViewModel by viewModels()

    val characterViewModel: CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlanetDescriptionBinding.inflate(inflater)

        viewModel.getPlanet(characterViewModel.planetId.value!!)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}