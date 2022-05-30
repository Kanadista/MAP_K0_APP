package com.example.map_k0.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.example.map_k0.R
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.ui.viewmodel.MapVM
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val viewModel: MapVM by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //createFragment()
        return inflater.inflate(R.layout.fragment_map, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMap(childFragmentManager, this@MapFragment)
    }
/*
    private fun createFragment(){
        val mapFragment: SupportMapFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
*/
    override fun onMapReady(mapaCreado: GoogleMap) {

        mapaCreado.apply{
            setMapStyle(MapStyleOptions.loadRawResourceStyle(requireActivity().applicationContext, R.raw.map_style));
            GoogleMapOptions().mapId("6c92c2dd989894a2");
        }
        map = mapaCreado
        setupObservers(map)
        viewModel.loadAllLocations()
    }

    private fun setupMap(childFragmentManager: FragmentManager, onMapReadyCallback: OnMapReadyCallback){
        (childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).apply {
            getMapAsync(onMapReadyCallback)
        }
    }

    private fun onMapListChange(locationList: List<LocationBO>,
                                googleMap: GoogleMap,
                                viewModel : MapVM){

        googleMap.apply {
            locationList.forEach{locationBO ->
                addMarker(
                    MarkerOptions()
                        .position(LatLng(locationBO.latitude, locationBO.longitude))
                        .title(locationBO.name)
                )
            }

        }
    }

    private fun setupObservers(googleMap: GoogleMap){
        viewModel.locationList.observe(viewLifecycleOwner) { onMapListChange(it, googleMap, viewModel)}
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}