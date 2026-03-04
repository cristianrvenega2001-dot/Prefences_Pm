package com.example.prefences_pm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.prefences_pm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instancia del ViewModel

        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)


        binding.apply {

            agregar.setOnClickListener {
                val email = editTextTextEmailAddress.text.toString()
                val contrasena = editTextTextPassword.text.toString()

                if(email.isNotEmpty() && contrasena.isNotEmpty()){
                    // Guardar datos a través del ViewModel
                    viewModel.guardarDatos(email.toString(),contrasena.toString())

                    // Limpiar campos después de guardar
                    editTextTextEmailAddress.setText("")
                    editTextTextPassword.setText("")

                    // Actualizar LiveData para mostrar datos
                     viewModel.mostrarDatos()
                }
            }

            // btn eliminar

            eliminar.setOnClickListener {
                // Llamamos al ViewModel para eliminar datos

                viewModel.eliminarDatos()

                //limpiar TextView

                textView2.text =""

            }

        }
        // OBSERVAR LiveData combinado para email y contraseña
        viewModel.emailLiveData.observe(this){actualizarTextView()}
        viewModel.contrasenaLiveData.observe(this){actualizarTextView()}

    }

    private fun actualizarTextView(){


        val email = viewModel.emailLiveData.value ?:""
        val contrasena = viewModel.contrasenaLiveData.value ?:""

        binding.textView2.text =  "Email: $email\nContraseña: $contrasena"
    }



}