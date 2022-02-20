package com.example.starwarsencyclopedia.ui.film

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.starwarsencyclopedia.databinding.FragmentCharacterDescriptionBinding
import com.example.starwarsencyclopedia.databinding.FragmentFilmDescriptionBinding
import com.example.starwarsencyclopedia.viewmodel.CharacterViewModel
import com.example.starwarsencyclopedia.viewmodel.FilmViewModel

class FilmDescriptionFragment : Fragment() {


    private val characterViewModel: CharacterViewModel by activityViewModels()

    private val viewModel: FilmViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFilmDescriptionBinding.inflate(inflater)

        viewModel.getFilm(characterViewModel.currentIds.value!!["film"]!!)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}