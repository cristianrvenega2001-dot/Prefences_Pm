package com.example.prefences_pm

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.MutableSharedFlow


class MyViewModel (application: Application): AndroidViewModel(application) {




      // Crear instancia de MyModel con SharedPreferences
        private val sharedPreferences= application.getSharedPreferences("mis_datos",
            Context.MODE_PRIVATE)

         private val myModel = MyModel(sharedPreferences)



        // LiveData para email y contraseña

      private val _emailLiveData = MutableLiveData<String>()
      val emailLiveData : LiveData<String> get() = _emailLiveData
       private val _contrasenaLiveData = MutableLiveData<String>()
       val  contrasenaLiveData : LiveData<String> get() = _contrasenaLiveData



    // Guardar datos y actualizar LiveData
    fun guardarDatos( email: String,contrasena :String){

        myModel.guardaDatos(email,contrasena)
    }

    // Eliminar datos y limpiar LiveData
    fun eliminarDatos(){

        myModel.removeDatos()
        _emailLiveData.value=""
        _contrasenaLiveData.value=""
    }

    // Mostrar datos: actualiza LiveData

    fun mostrarDatos(){

        val(email,contrasena) = myModel.mostrar()

        _emailLiveData.value = email ?: ""
        _contrasenaLiveData.value = contrasena ?:""
    }




}