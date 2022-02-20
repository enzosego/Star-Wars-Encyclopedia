package com.example.starwarsencyclopedia.ui.character

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.starwarsencyclopedia.R
import com.example.starwarsencyclopedia.adapter.*
import com.example.starwarsencyclopedia.adapter.data.DataSource
import com.example.starwarsencyclopedia.databinding.FragmentCharacterDescriptionBinding
import com.example.starwarsencyclopedia.viewmodel.CharacterViewModel

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

        binding.homeworld.text =
            DataSource.planetNames[viewModel.planetId.value!!]

        binding.homeworld.setOnClickListener {
            findNavController()
                .navigate(R.id.action_characterDescriptionFragment_to_planetDescriptionFragment)
        }

        binding.filmListRecycler.adapter = FilmListAdapter(FilmListener { filmId ->
            viewModel.onFilmClicked(filmId)
            findNavController()
                .navigate(R.id.action_characterDescriptionFragment_to_filmDescriptionFragment)
        })

        binding.vehicleListRecycler.adapter = VehicleListAdapter(VehicleListener { vehicleId ->
            viewModel.onVehicleClicked(vehicleId)
            findNavController()
                .navigate(R.id.action_characterDescriptionFragment_to_vehicleDescriptionFragment)
        })

        binding.starshipListRecycler.adapter = StarshipListAdapter(StarshipListener { starshipId ->
            viewModel.onStarshipClicked(starshipId)
            findNavController()
                .navigate(R.id.action_characterDescriptionFragment_to_starshipDescriptionFragment)
        })

        return binding.root
    }
}