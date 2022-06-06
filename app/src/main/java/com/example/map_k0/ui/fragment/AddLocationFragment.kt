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
import androidx.lifecycle.lifecycleScope
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
    private val list : LinkedList<Uri> = LinkedList()
    private var locationCreated : LocationBO? = null
    private val imageLauncher = registerForActivityResult(StartActivityForResult()){

        if (it.resultCode == RESULT_OK) {
            if (it.data?.getClipData() != null) {
                val count: Int = it.data?.getClipData()!!.getItemCount()
                if(count == 1){
                    list.add(it.data?.getClipData()!!.getItemAt(0).getUri())
                }else{
                    for (i in 0 until count) {
                        list.add(it.data?.getClipData()!!.getItemAt(i).getUri())
                    }
                }
            }
        }
    }

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

                    viewModel.lastLocation.observe(viewLifecycleOwner) {
                        locationCreated = it
                        insertImages()
                    }
            }

            addImagesBtn.setOnClickListener{
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                imageLauncher.launch(intent)
            }
        }
        return binding?.root
    }
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            binding = FragmentAddLocationBinding.inflate(layoutInflater)
            return MaterialAlertDialogBuilder(requireActivity()).setView(binding?.root).create()
        }


    private fun insertImages(){
        list.forEach {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
               val bitmap :Bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver, it))
                locationCreated?.let { location ->
                    viewModel.createLocationImage(LocationImageBO(idLocation = location.id, image = bitmap))
                }
            }
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
