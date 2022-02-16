package com.example.starwarsencyclopedia.ui.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.starwarsencyclopedia.databinding.FragmentCharacterDescriptionBinding
import com.example.starwarsencyclopedia.model.CharacterViewModel

class CharacterDescriptionFragment : Fragment() {

    val viewModel : CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharacterDescriptionBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        if (viewModel.currentCharacter.value != null) {
            Log.d("debug", viewModel.currentCharacter.value!!.name)
        }

        return binding.root
    }
}