package com.example.starwarsencyclopedia.ui.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.starwarsencyclopedia.R
import com.example.starwarsencyclopedia.adapter.CharacterListAdapter
import com.example.starwarsencyclopedia.adapter.CharacterListener
import com.example.starwarsencyclopedia.databinding.FragmentCharacterListBinding
import com.example.starwarsencyclopedia.model.CharacterViewModel
import kotlin.system.exitProcess

class CharacterListFragment : Fragment() {

    private val viewModel: CharacterViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharacterListBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.characterListRecycler.adapter = CharacterListAdapter(CharacterListener { character ->
            viewModel.onCharacterClicked(character)
            findNavController()
                .navigate(R.id.action_characterListFragment_to_characterDescriptionFragment)
        })

        viewModel.switchDescriptionDisplayStatus(false)

        return binding.root
    }
}