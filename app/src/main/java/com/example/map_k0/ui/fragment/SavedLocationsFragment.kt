package com.example.map_k0.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.map_k0.R
import com.example.map_k0.databinding.FragmentSavedLocationsBinding
import com.example.map_k0.ui.view.adapter.LocationAdapter
import com.example.map_k0.ui.view.adapter.LocationImageAdapter
import com.example.map_k0.ui.view.base.BaseFragment
import com.example.map_k0.ui.viewmodel.AddEventVM
import com.example.map_k0.ui.viewmodel.SavedLocationsVM
import com.google.firebase.auth.FirebaseAuth


class SavedLocationsFragment : BaseFragment<FragmentSavedLocationsBinding>() {

    private val viewModel: SavedLocationsVM by viewModels()
    private lateinit var locationAdapter : LocationAdapter


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

            if(FirebaseAuth.getInstance().currentUser != null){
                viewModel.getSavedLocation(FirebaseAuth.getInstance().uid.toString())
            }else{
                showUnloggedSavedAlert()
            }

            viewModel.locationList.observe(viewLifecycleOwner){ locations ->
                locationAdapter = LocationAdapter(locations, onClickListener = {
                    findNavController().navigate(SavedLocationsFragmentDirections.actionSavedLocationsFragmentToMapFragment(
                        it.latitude.toString(), it.longitude.toString()
                    ))

                })
                recyclerSavedLocation.adapter = locationAdapter
                recyclerSavedLocation.addItemDecoration(DividerItemDecoration(context, 1))
            }
        }
    }

    private fun showUnloggedSavedAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Necesitas iniciar sesión para tener localizaciones guardadas.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}