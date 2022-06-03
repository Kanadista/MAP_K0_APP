package com.example.map_k0.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.map_k0.R
import com.example.map_k0.databinding.FragmentAddLocationBinding
import com.example.map_k0.databinding.LocationDetailsBinding
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.ui.viewmodel.AddLocationVM
import com.example.map_k0.ui.viewmodel.DetailDialogFragmentVM
import com.example.map_k0.ui.viewmodel.MapVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddLocationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddLocationFragment : DialogFragment() {

    private var binding: FragmentAddLocationBinding? = null
    private val args: AddLocationFragmentArgs by navArgs()
    private val viewModel: AddLocationVM by viewModels()
    private val viewModelMap : MapVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding?.apply {
            addButton.setOnClickListener {

                    val locationBO = LocationBO(
                        0,
                        addTitleInputEditText.text.toString(),
                        addDescriptionInputEditText.text.toString(),
                        args.latitude.toDouble(),
                        args.longitud.toDouble(),
                        FirebaseAuth.getInstance().uid.toString()
                    )
                    viewModel.createLocation(locationBO)
                    viewModelMap.loadAllLocations()
                    successfullCreationAlert()
            }
        }
        return binding?.root
    }
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            binding = FragmentAddLocationBinding.inflate(layoutInflater)
            return MaterialAlertDialogBuilder(requireActivity()).setView(binding?.root).create()
        }



    private fun successfullCreationAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Tarea completada")
        builder.setMessage("La localización se ha creado correctamente.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    }
