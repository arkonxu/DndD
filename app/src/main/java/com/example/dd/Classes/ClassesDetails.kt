package com.example.dd.com.example.dd.Classes

import android.content.Context
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.widget.ExpandableListAdapter
import com.example.dd.setDrawable
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.details_classes.*
import java.net.URL
import android.widget.ExpandableListView
import com.example.dd.Classes.*
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

    fun apiFrom() : List<From> {
       var from =  apiClass().proficiency_choices[0].from
        return from
    }

    fun apiAbility() : List<SavingThrow> {
        var abilities =  apiClass().saving_throws
        return abilities
    }

    fun apiEquipment() : List<StartingEquipmentX> {
        val builder = GsonBuilder()
        val gson = builder.create()
        val json = URL(apiClass().starting_equipment.url).readText()
        var equipment = gson.fromJson(json, StartingEquipmentClass::class.java)
        return equipment.starting_equipment
    }

    fun apiSub() : List<Subclasse> {
        var sub = apiClass().subclasses
        return sub
    }


    fun expandableList(){


        val expandableListView: ExpandableListView = findViewById(R.id.list1)

        var listaProficiency:List<String> = apiClass().proficiencies.map { it.name }
        var listaProficiencyChoice :List<String> =  apiFrom().map { it.name }
        var listaAbilities :List<String> =  apiAbility().map { it.name }
        var listaEquipment :List<String> =  apiEquipment().map { it.item.name }
        var listaSub:List<String> =  apiSub().map { it.name }





        var listaNombres = ArrayList<String>()
        listaNombres.add("Proficiencies")
        listaNombres.add("Proficiencies Choices")
        listaNombres.add("Ability Scores")
        listaNombres.add("Starting Equipment")
        listaNombres.add("Subclasses")



        var listHash = ArrayList<List<String>>()
        listHash.add(listaProficiency)
        listHash.add(listaProficiencyChoice)
        listHash.add(listaAbilities)
        listHash.add(listaEquipment)
        listHash.add(listaSub)

        var adapter = ExpandableListViewAdapter(listaNombres,this, listHash)

        expandableListView.setAdapter(adapter)
    }
}