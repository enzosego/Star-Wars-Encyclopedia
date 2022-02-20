package com.example.starwarsencyclopedia.ui.vehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.starwarsencyclopedia.databinding.FragmentCharacterDescriptionBinding
import com.example.starwarsencyclopedia.databinding.FragmentVehicleDescriptionBinding
import com.example.starwarsencyclopedia.viewmodel.CharacterViewModel
import com.example.starwarsencyclopedia.viewmodel.VehicleViewModel

class VehicleDescriptionFragment : Fragment() {

    val characterViewModel: CharacterViewModel by activityViewModels()

    val viewModel: VehicleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVehicleDescriptionBinding.inflate(inflater)

        viewModel.getVehicle(characterViewModel.currentIds.value!!["vehicle"]!!)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}