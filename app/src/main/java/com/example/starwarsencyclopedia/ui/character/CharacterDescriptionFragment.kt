package com.example.starwarsencyclopedia.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.starwarsencyclopedia.databinding.FragmentCharacterDescriptionBinding
import com.example.starwarsencyclopedia.model.CharacterViewModel

class CharacterDescriptionFragment : Fragment() {

    val viewModel : CharacterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharacterDescriptionBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}