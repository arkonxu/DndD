package com.example.dd.com.example.dd.Classes

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.widget.ExpandableListAdapter
import com.example.dd.Classes.Class
import com.example.dd.Classes.From
import com.example.dd.Classes.Proficiency
import com.example.dd.Classes.Result
import com.example.dd.setDrawable
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.details_classes.*
import java.net.URL
import android.widget.ExpandableListView
import com.example.dd.R


class ClassesDetails : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.dd.R.layout.details_classes)

        expandableList()
        var clases : Result = intent.extras.get("CLASE") as Result
        nombreClase.text=clases.name
        imagenClaseDetail.setDrawable(clases.name.decapitalize())
        descripcion.text="Hit points: "+apiClass().hit_die

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

    fun expandableList(){


        val expandableListView: ExpandableListView = findViewById(R.id.list1)

        var listaProficiency:List<String> = apiClass().proficiencies.map { it.name }
        var listaProficiencyChoice= ArrayList<String>()//= apiClass().proficiency_choices.map { it. }

        listaProficiencyChoice.add("AAAAAAAAAAAAA")

        var listaNombres = ArrayList<String>()
        listaNombres.add("Proficiencies")
        listaNombres.add("Proficiencies Choices")
        listaNombres.add("Ability Scores")
        listaNombres.add("Starting Equipment")
        listaNombres.add("Class Levels")
        listaNombres.add("Subclasses")
        listaNombres.add("Spellcasting")



        var listHash = ArrayList<List<String>>()
        listHash.add(listaProficiency)
        listHash.add(listaProficiencyChoice)

        var adapter = ExpandableListViewAdapter(listaNombres,this, listHash)

        expandableListView.setAdapter(adapter)
    }
}