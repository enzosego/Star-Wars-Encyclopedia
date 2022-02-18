package com.example.starwarsencyclopedia.ui.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.starwarsencyclopedia.R
import com.example.starwarsencyclopedia.adapter.CharacterListAdapter
import com.example.starwarsencyclopedia.adapter.CharacterListener
import com.example.starwarsencyclopedia.databinding.FragmentCharacterListBinding
import com.example.starwarsencyclopedia.model.CharacterViewModel

class CharacterListFragment : Fragment() {

    private val viewModel: CharacterViewModel by activityViewModels()

    private lateinit var binding: FragmentCharacterListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterListBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.characterListRecycler.adapter = CharacterListAdapter(CharacterListener { character ->
            viewModel.onCharacterClicked(character)
            findNavController()
                .navigate(R.id.action_characterListFragment_to_characterDescriptionFragment)
        })

        binding.searchInput.addTextChangedListener {
            viewModel.filterCharacters(it!!.toString())
            if (it.isBlank()) {
                viewModel.refreshPage()
                viewModel.switchSearchingStatus(false)
            }
        }

        viewModel.switchDescriptionDisplayStatus()

        return binding.root
    }
}