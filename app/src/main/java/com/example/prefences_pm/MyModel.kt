package com.example.prefences_pm

import android.content.SharedPreferences


class MyModel (private val sharedPreferences: SharedPreferences)
             {

    companion object{

        const val  EMAIL_KEY = "emailKey"
        const val CONTRASENA_KEY = "contrasenaKey"
    }

                 // Guardar datos en SharedPreferences
                 fun guardaDatos(email: String, contrasena: String){

                     sharedPreferences.edit().apply{

                         putString(EMAIL_KEY,email)
                         putString(CONTRASENA_KEY,contrasena)
                         apply()// guardar cambios

                     }


                 }

                 // Eliminar datos de SharedPreferences

                 fun removeDatos(){

                     sharedPreferences.edit().apply{

                         remove(EMAIL_KEY)
                         remove(CONTRASENA_KEY)
                         apply()
                     }
                 }





                 // Obtener un valor específico de SharedPreferences

                 fun getFromPreferences( key : String): String?{
                     return sharedPreferences.getString(key,"")
                 }




                 // Mostrar email y contraseña guardados

                 fun mostrar() : Pair<String?,String?> {

                     val email = getFromPreferences(EMAIL_KEY)
                     val contrasena = getFromPreferences(CONTRASENA_KEY)
                     return  Pair ( email,contrasena)
                 }



                 // Agregar un listado de emails y contraseñas
                 fun agregarListadoDeCorreosYClaves(emails : List<String>,claves :List<String>){


                     sharedPreferences.edit().apply(){


                         emails.forEachIndexed { index, email ->

                             putString("email_$index",email)
                         }

                         claves.forEachIndexed { index, clave ->
                             putString("contraseña_$index",clave)
                         }
                         apply()
                     }



                 }








}