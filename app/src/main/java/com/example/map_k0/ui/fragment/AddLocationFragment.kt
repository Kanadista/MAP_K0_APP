package com.example.map_k0.ui.fragment

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.map_k0.databinding.FragmentAddLocationBinding
import com.example.map_k0.domain.entities.LocationBO
import com.example.map_k0.ui.viewmodel.AddLocationVM
import com.example.map_k0.ui.viewmodel.MapVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import android.net.Uri
import java.util.*

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.map_k0.R
import com.example.map_k0.domain.entities.LocationImageBO
import kotlinx.coroutines.launch
import java.lang.Thread.sleep


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
    //private val list : LinkedList<Uri> = LinkedList()
    private var image : Uri? = null
    private var locationCreated : LocationBO? = null
    private val imageLauncher = registerForActivityResult(StartActivityForResult()){

        if (it.resultCode == RESULT_OK) {
          /*  if (it.data?.clipData != null) {                 ESTE CÓDIGO FUNCIONARÍA SI LA API DE AZURE RESPONDIESE MÁS RÁPIDO
                val count: Int = it.data?.getClipData()!!.getItemCount()
                if(count == 1){
                    list.add(it.data?.getClipData()!!.getItemAt(0).getUri())
                }else{
                    for (i in 0 until count) {
                        list.add(it.data?.getClipData()!!.getItemAt(i).getUri())
                    }
                }
            } */

            image = it.data?.data

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding?.apply {
            addButton.setOnClickListener {
                    createAnimation.visibility = View.VISIBLE
                    cardViewAnimation.visibility = View.VISIBLE
                    addLinear.setBackgroundColor(ContextCompat.getColor(context!!, R.color.loading_gray))
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

                    viewModel.lastLocation.observe(viewLifecycleOwner) {
                        locationCreated = it
                        insertImages(it)
                    }
            }

            addImagesBtn.setOnClickListener{
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                imageLauncher.launch(intent)
            }
        }

        viewModel.created.observe(viewLifecycleOwner){
            if(it){
            getListener()?.locationAdded()
                binding?.createAnimation?.visibility = View.GONE
                binding?.cardViewAnimation?.visibility = View.GONE
            successfullCreationAlert()
            dismiss()
            }
        }

        return binding?.root
    }
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            binding = FragmentAddLocationBinding.inflate(layoutInflater)
            return MaterialAlertDialogBuilder(requireActivity()).setView(binding?.root).create()
        }


    private fun insertImages(locationBO: LocationBO){
        /*list.forEach {   ESTE SERÍA EL CÓDIGO PARA INSERTAR LAS IMAGENES EN LA API SI RESPONDIESE LO SUFICIENTEMENTE RAPIDO
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
               val bitmap :Bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver, it))
                locationCreated?.let { location ->
                    viewModel.createLocationImage(LocationImageBO(idLocation = location.id, image = bitmap))
                }
            }
        } */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val bitmap :Bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver, image!!))
            viewModel.createLocationImage(LocationImageBO(idLocation = locationBO.id, image = bitmap))
        }
    }

    private fun getListener(): AddLocationListener? {
        val targetFragmentIndex = parentFragmentManager.fragments.size - 2
        val targetFragment = parentFragmentManager.fragments[targetFragmentIndex]
        return when {
            targetFragment is AddLocationListener -> targetFragment as AddLocationListener
            parentFragment is AddLocationListener -> parentFragment as AddLocationListener
            activity is AddLocationListener -> activity as AddLocationListener
            else -> null
        }
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

interface AddLocationListener{

    fun locationAdded()
}
