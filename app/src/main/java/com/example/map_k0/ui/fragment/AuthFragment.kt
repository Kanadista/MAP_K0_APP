package com.example.map_k0.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.map_k0.R
import com.example.map_k0.databinding.FragmentAuthBinding
import com.example.map_k0.databinding.FragmentMapBinding
import com.example.map_k0.ui.view.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AuthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthFragment : BaseFragment<FragmentAuthBinding>() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        binding?.apply {
            signUpButton.setOnClickListener{
                if (editTextEmail.text.isNotEmpty() && editTextPassword.text.isNotEmpty()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(editTextEmail.text.toString(),
                        editTextPassword.text.toString()).addOnCompleteListener{
                        if( it.isSuccessful){
                            showSuccessSignUpAlert()
                        }else{
                            showFailureAlert()
                        }
                    }
                }
            }

            loginButton.setOnClickListener{
                if (editTextEmail.text.isNotEmpty() && editTextPassword.text.isNotEmpty()){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextEmail.text.toString(),
                        editTextPassword.text.toString()).addOnCompleteListener{
                        if(it.isSuccessful){
                            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToMapFragment())
                        }else{
                            showFailureAlert()
                        }
                    }
                }
            }
        }
        return binding?.root
    }


    private fun showFailureAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showSuccessSignUpAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Éxito")
        builder.setMessage("Se ha registrado al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showSuccessLogInAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Éxito")
        builder.setMessage("Se ha iniciado sesion con el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}