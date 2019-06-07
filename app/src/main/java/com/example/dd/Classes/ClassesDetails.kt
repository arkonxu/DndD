package com.example.dd.com.example.dd.Classes

import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import com.example.dd.Classes.Class
import com.example.dd.Classes.Classes
import com.example.dd.Classes.Result
import com.example.dd.R
import com.example.dd.setDrawable
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_classes.*
import java.net.URL

class ClassesDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_classes)

        var clases : Result = intent.extras.get("CLASE") as Result
        nombreClase.text=clases.name
        imagenClaseDetail.setDrawable(clases.name.decapitalize())
        descripcion.text="Subclasses: "+apiClass().subclasses.get(0).name

    }

    fun apiClass() : Class {
        var clases : Result = intent.extras.get("CLASE") as Result
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        val builder = GsonBuilder()
        val gson = builder.create()
        val json = URL(clases.url).readText()
        var clase = gson.fromJson(json, Class::class.java)
        return clase
    }
}