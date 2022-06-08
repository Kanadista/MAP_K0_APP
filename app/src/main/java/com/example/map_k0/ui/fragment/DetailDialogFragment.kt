package com.example.map_k0.ui.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.map_k0.databinding.LocationDetailsBinding
import com.example.map_k0.domain.entities.UserRatingLocationBO
import com.example.map_k0.domain.entities.UserSavedLocationsBO
import com.example.map_k0.ui.model.LocationWithRatingsAndImage
import com.example.map_k0.ui.view.adapter.LocationImageAdapter
import com.example.map_k0.ui.view.adapter.RatingAdapter
import com.example.map_k0.ui.viewmodel.DetailDialogFragmentVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth


class DetailDialogFragment : DialogFragment() {

    private var binding: LocationDetailsBinding? = null
    private val args: DetailDialogFragmentArgs by navArgs()
    private val viewModel: DetailDialogFragmentVM by viewModels()
    private var locationWithRatingsAndImage: LocationWithRatingsAndImage? = null
    private var savedList : List<UserSavedLocationsBO>? = null
    private val adapter by lazy { RatingAdapter() }
    private var saved : Boolean = false
    private lateinit var imageAdapter : LocationImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding?.apply {

            viewModel.userSavedLocations.observe(viewLifecycleOwner){
                savedList = it
            }

            if(FirebaseAuth.getInstance().currentUser != null){
                viewModel.getUserSavedLocationsByUserId(FirebaseAuth.getInstance().uid.toString())
            }


            detailSendButton.setOnClickListener {
                if (locationWithRatingsAndImage != null) {
                    if(FirebaseAuth.getInstance().currentUser != null){
                    val userRatingLocationBO = UserRatingLocationBO(
                        FirebaseAuth.getInstance().uid.toString(), args.locationId,
                        detailRating.rating.toInt(), detailComment.text.toString()
                    )
                        if(detailComment.text.toString() == ""){
                            showWrongCommentAlert()
                        }else{
                            detailComment.setText("")
                            viewModel.createUserRatingLocation(userRatingLocationBO)
                            viewModel.getRatings(args.locationId)
                        }

                     }else {
                        showUnloggedAddAlert()
                    }
                }
            }

            saveButton.setOnClickListener{
                if(FirebaseAuth.getInstance().currentUser != null) {
                    if(savedList?.any{ it.idLocation == args.locationId}!! || saved){
                        showAlreadySavedAlert()
                    }else {
                        viewModel.createUserSavingLocation(
                            UserSavedLocationsBO(
                                FirebaseAuth.getInstance().uid.toString(),
                                args.locationId
                            )
                        )
                        saved = true
                        showLocationSavedAlert()
                    }
                }else{
                    showUnloggedSaveAlert()
                }
            }
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.locationWithRating.observe(viewLifecycleOwner) {
            binding?.onLocationChanged(it)
        }

        viewModel.ratings.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.getLocationWithRating(args.locationId)

    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = LocationDetailsBinding.inflate(layoutInflater)
        return MaterialAlertDialogBuilder(requireActivity()).setView(binding?.root).create()
    }

    private fun LocationDetailsBinding.onLocationChanged(locationWithRating: LocationWithRatingsAndImage) {
        locationWithRatingsAndImage = locationWithRating
        detailTitle.text = locationWithRating.name
        detailDescription.text = locationWithRating.description
        if (locationWithRating.ratings.isNotEmpty()) {
            detailRating.rating = (locationWithRating.ratings.sumOf { it.stars } / locationWithRating.ratings.size).toFloat()
        }
        detailRecycler.adapter = adapter
        detailRecycler.addItemDecoration(DividerItemDecoration(context, 1))
        adapter.submitList(locationWithRating.ratings)
        imageAdapter = LocationImageAdapter(locationWithRatingsAndImage!!.images)
        val manager = LinearLayoutManager(view!!.context, LinearLayoutManager.HORIZONTAL, false)
        val recyclerView = binding!!.detailImage
        recyclerView.layoutManager = manager
        recyclerView.adapter = imageAdapter
        imageAdapter.notifyDataSetChanged()
    }

    private fun showUnloggedAddAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Necesitas iniciar sesión para comentar una localización.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showUnloggedSaveAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Necesitas iniciar sesión para guardar una localización.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlreadySavedAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Esa localización ya ha sido guardada.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    private fun showLocationSavedAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Tarea completada")
        builder.setMessage("Localización guardada correctamente.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showWrongCommentAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("El comentario no puede estar vacío.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}
