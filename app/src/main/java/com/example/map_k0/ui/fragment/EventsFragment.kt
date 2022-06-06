package com.example.map_k0.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.compose.ui.layout.Layout
import androidx.fragment.app.viewModels
import com.example.map_k0.R
import com.example.map_k0.databinding.FragmentEventsBinding
import com.example.map_k0.ui.view.adapter.EventAdapter
import com.example.map_k0.ui.viewmodel.EventsVM
import kotlinx.coroutines.NonDisposableHandle.parent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class EventsFragment : Fragment() {

    private val viewModel: EventsVM by viewModels()
    private var binding : FragmentEventsBinding? = null
    private val adapter by lazy { EventAdapter() }


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
            recyclerEvents.adapter = adapter
            viewModel.loadAllEvents()
        }

        viewModel.eventList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }
}