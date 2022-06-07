package com.example.map_k0.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.map_k0.R
import com.example.map_k0.databinding.FragmentAddEventBinding
import com.example.map_k0.databinding.FragmentAddLocationBinding
import com.example.map_k0.domain.entities.EventBO
import com.example.map_k0.ui.viewmodel.AddEventVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDate
import java.util.Date
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class AddEventFragment : DialogFragment() {

    private var binding : FragmentAddEventBinding? = null
    private val viewModel: AddEventVM by viewModels()
    private val typeList = listOf("Benefico", "Deporte", "Cultural", "Activismo")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding?.apply {


                val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_dropdown_item, typeList)
                eventSpinner.adapter = adapter


            addEventButton.setOnClickListener {
            var eventBO = EventBO(
                0,
                addEventTitleInputEditText.text.toString(),
                addEventDescriptionInputEditText.text.toString(),
                addEventAddressInputEditText.text.toString(),
                eventSpinner.selectedItemPosition,
                FirebaseAuth.getInstance().uid.toString(),
                createDate(eventDatePicker)
            )
            viewModel.createEvent(eventBO)
            }
        }

        viewModel.created.observe(viewLifecycleOwner){
            if(it) {
                getListener()?.eventAdded()
                successfullCreationAlert()
                dismiss()
            }
        }

        return binding?.root
    }

    private fun getListener(): AddEventListener? {
        val targetFragmentIndex = parentFragmentManager.fragments.size - 2
        val targetFragment = parentFragmentManager.fragments[targetFragmentIndex]
        return when {
            targetFragment is AddEventListener -> targetFragment as AddEventListener
            parentFragment is AddEventListener -> parentFragment as AddEventListener
            activity is AddEventListener -> activity as AddEventListener
            else -> null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentAddEventBinding.inflate(layoutInflater)
        return MaterialAlertDialogBuilder(requireActivity()).setView(binding?.root).create()
    }

    private fun createDate(datePicker: DatePicker): Date {
        val cal = Calendar.getInstance()
        cal[Calendar.YEAR] = datePicker.year
        cal[Calendar.MONTH] = datePicker.month
        cal[Calendar.DAY_OF_MONTH] = datePicker.dayOfMonth
        return cal.time as Date
    }

    private fun successfullCreationAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Tarea completada")
        builder.setMessage("El evento se ha creado correctamente.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}



interface AddEventListener{
    fun eventAdded()
}