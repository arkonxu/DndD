package com.example.dd.Classes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.net.URL;
import com.google.gson.GsonBuilder;
import android.view.View
import android.os.StrictMode
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.dd.R
import com.example.dd.manejar


class MainActivityClasses : AppCompatActivity()  {

    private lateinit var manager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rv: RecyclerView = findViewById(R.id.rv)
        var clases:List<Result> = apiClases().results

        manager = LinearLayoutManager(this)
        rv.manejar(manager,clases,this)

    }

    private fun apiClases(): Classes {

            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val builder = GsonBuilder()
            val gson = builder.create()
            val json = URL("http://dnd5eapi.co/api/classes").readText()
            var api = gson.fromJson(json, Classes::class.java)
            return api

    }

}
