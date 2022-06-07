package com.example.map_k0.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.map_k0.R
import com.example.map_k0.databinding.FragmentSavedLocationsBinding
import com.example.map_k0.ui.view.base.BaseFragment
import com.example.map_k0.ui.viewmodel.AddEventVM


class SavedLocationsFragment : BaseFragment<FragmentSavedLocationsBinding>() {

    private val viewModel: AddEventVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedLocationsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            setupDrawerWithFragmentToolbar(locationFragmentToolbarTop)

        }
    }
}