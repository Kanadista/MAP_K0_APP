package com.example.map_k0.ui.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.map_k0.R
import com.example.map_k0.databinding.FragmentAuthBinding
import com.example.map_k0.databinding.FragmentMapBinding
import com.example.map_k0.ui.view.base.BaseFragment
import com.google.firebase.auth.FirebaseAuth

class AuthFragment : BaseFragment<FragmentAuthBinding>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        binding?.apply {

            setupDrawerWithFragmentToolbar(locationFragmentToolbarTop)
            authRegisterMode.setOnClickListener{
                authUserMail.visibility = View.VISIBLE
                authUserMailEditText.visibility = View.VISIBLE
                authUserPasswordInput.visibility = View.VISIBLE
                authUserPasswordEditText.visibility = View.VISIBLE
                authUserPassword2Input.visibility = View.VISIBLE
                authUserPassword2EditText.visibility = View.VISIBLE
                authUserNameEditText.visibility = View.VISIBLE
                authUserNameInput.visibility = View.VISIBLE
                authUserLastNameEditText.visibility = View.VISIBLE
                authUserLastNameInput.visibility = View.VISIBLE
                authUserAddressEditText.visibility = View.VISIBLE
                authUserAddressInput.visibility = View.VISIBLE
                signUpButton.visibility = View.VISIBLE
                loginButton.visibility = View.GONE
                authLoginMode.setTextColor(ContextCompat.getColor(context!!, R.color.grey))
                authRegisterMode.setTextColor(ContextCompat.getColor(context!!, R.color.orange_500))
            }

            authLoginMode.setOnClickListener{
                authUserMail.visibility = View.VISIBLE
                authUserMailEditText.visibility = View.VISIBLE
                authUserPasswordInput.visibility = View.VISIBLE
                authUserPasswordEditText.visibility = View.VISIBLE
                authUserPassword2Input.visibility = View.GONE
                authUserPassword2EditText.visibility = View.GONE
                authUserNameEditText.visibility = View.GONE
                authUserNameInput.visibility = View.GONE
                authUserLastNameEditText.visibility = View.GONE
                authUserLastNameInput.visibility = View.GONE
                authUserAddressEditText.visibility = View.GONE
                authUserAddressInput.visibility = View.GONE
                signUpButton.visibility = View.GONE
                loginButton.visibility = View.VISIBLE
                authLoginMode.setTextColor(ContextCompat.getColor(context!!, R.color.orange_500))
                authRegisterMode.setTextColor(ContextCompat.getColor(context!!, R.color.grey))
            }


            signUpButton.setOnClickListener{
                if (authUserMailEditText.text?.isNotEmpty()!! && authUserPasswordEditText.text?.isNotEmpty()!!){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(authUserMailEditText.text.toString(),
                        authUserPasswordEditText.text.toString()).addOnCompleteListener{
                        if( it.isSuccessful){
                            showSuccessSignUpAlert()
                        }else{
                            showFailureAlert()
                        }
                    }
                }
            }

            loginButton.setOnClickListener{
                if (authUserMailEditText.text?.isNotEmpty()!! && authUserPasswordEditText.text?.isNotEmpty()!!){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(authUserMailEditText.text.toString(),
                        authUserPasswordEditText.text.toString()).addOnCompleteListener{
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