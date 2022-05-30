package com.example.map_k0.ui.view.activity

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.map_k0.R
import com.example.map_k0.databinding.ActivityAuthBinding
import com.google.firebase.auth.FirebaseAuth


class AuthActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

      // val signUpButton =  findViewById<Button>(R.id.signUpButton)
       val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
       val editTextPassword = findViewById<EditText>(R.id.editTextPassword)


        binding.signUpButton.setOnClickListener{
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

        binding.loginButton.setOnClickListener{
            if (editTextEmail.text.isNotEmpty() && editTextPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(editTextEmail.text.toString(),
                    editTextPassword.text.toString()).addOnCompleteListener{
                    if( it.isSuccessful){
                        showSuccessLogInAlert()
                    }else{
                        showFailureAlert()
                    }
                }
            }
        }
    }


    private fun showFailureAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showSuccessSignUpAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Éxito")
        builder.setMessage("Se ha registrado al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showSuccessLogInAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Éxito")
        builder.setMessage("Se ha iniciado sesion con el usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}