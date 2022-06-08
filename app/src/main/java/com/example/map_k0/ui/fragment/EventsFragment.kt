package com.example.map_k0.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.compose.ui.layout.Layout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.map_k0.R
import com.example.map_k0.databinding.FragmentEventsBinding
import com.example.map_k0.domain.entities.EventAssistanceBO
import com.example.map_k0.ui.view.adapter.EventAdapter
import com.example.map_k0.ui.view.base.BaseFragment
import com.example.map_k0.ui.viewmodel.EventsVM
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.NonDisposableHandle.parent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class EventsFragment : BaseFragment<FragmentEventsBinding>(), AddEventListener {

    private val viewModel: EventsVM by viewModels()
    private val adapter by lazy { EventAdapter(onClickListener = {
        if (FirebaseAuth.getInstance().currentUser != null) {
        viewModel.createEventAssistance(EventAssistanceBO(it.id, FirebaseAuth.getInstance().uid.toString()))
        }
    })}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            setupDrawerWithFragmentToolbar(locationFragmentToolbarTop)
            recyclerEvents.adapter = adapter
            viewModel.loadAllEvents()

            addEventListBtn.setOnClickListener {
                if (FirebaseAuth.getInstance().currentUser != null) {
                    findNavController().navigate(
                        EventsFragmentDirections.actionEventFragmentToAddEventFragment()
                    )
                }else{
                    showUnloggedAlert()
                }
            }
        }

        viewModel.eventList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private fun showUnloggedAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Necesitas iniciar sesión para crear una localización.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun eventAdded() {
        viewModel.loadAllEvents()
    }
}