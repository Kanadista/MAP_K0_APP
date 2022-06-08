package com.example.map_k0.ui.fragment

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.map_k0.R
import com.example.map_k0.databinding.FragmentMapBinding
import com.example.map_k0.databinding.LocationDetailsBinding
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.ui.view.base.BaseFragment
import com.example.map_k0.ui.viewmodel.MapVM
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapFragment : BaseFragment<FragmentMapBinding>(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener, AddLocationListener {

    private lateinit var map: GoogleMap
    private val viewModel: MapVM by viewModels()
    private val args : MapFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentMapBinding.inflate(layoutInflater, container, false)
        binding?.apply {
            setupDrawerWithFragmentToolbar(locationFragmentToolbarTop)

            helpBtn.setOnClickListener{showHelpAlert()}
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMap(childFragmentManager, this@MapFragment)
    }

    override fun onMapReady(mapaCreado: GoogleMap) {

        mapaCreado.apply{
            setMapStyle(MapStyleOptions.loadRawResourceStyle(requireActivity().applicationContext, R.raw.map_style));
            GoogleMapOptions().mapId("6c92c2dd989894a2");
        }
        map = mapaCreado
        setupObservers(map)
        viewModel.loadAllLocations()

        if(args.latitude != "-1" && args.longitud != "-1"){
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(args.latitude.toDouble(), args.longitud.toDouble()), 90F))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
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
            setOnMapLongClickListener { onMapLongClicked(it) }
            setOnMarkerClickListener(this@MapFragment)
            locationList.forEach{locationBO ->
                addMarker(
                    MarkerOptions()
                        .position(LatLng(locationBO.latitude, locationBO.longitude))
                        .title(locationBO.name)
                )
                    ?.tag = locationBO.id
            }
        }
    }

    private fun onMapLongClicked(point: LatLng) {
        if (FirebaseAuth.getInstance().currentUser != null) {
            findNavController().navigate(
                MapFragmentDirections.actionMapFragmentToAddLocationFragment(
                    point.latitude.toString(), point.longitude.toString()
                )
            )
        }else{
            showUnloggedAlert()
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        onMarkerClickedDetails(marker.tag as Int)
        return true
    }

    private fun onMarkerClickedDetails(locationId: Int){
        findNavController().navigate(MapFragmentDirections.actionMapFragmentToDetailDialogFragment(locationId))
    }

    private fun setupObservers(googleMap: GoogleMap){
        viewModel.locationList.observe(viewLifecycleOwner) { onMapListChange(it, googleMap, viewModel)}
    }

    private fun showUnloggedAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Necesitas iniciar sesión para crear una localización.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHelpAlert(){
        val dialogBuilder = AlertDialog.Builder(context)
        val dialogView = layoutInflater.inflate(R.layout.help_dialog, null)
        dialogBuilder.setView(dialogView)
        dialogBuilder.setPositiveButton("Aceptar", null)
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    override fun locationAdded() {
        viewModel.loadAllLocations()
    }


}