package com.example.dd.Classes

import android.app.PendingIntent.getActivity
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.net.URL;
import com.google.gson.GsonBuilder;
import android.os.StrictMode
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.dd.R
import com.example.dd.com.example.dd.Classes.DividerList
import com.example.dd.manejar
import com.rbddevs.splashy.Splashy


class MainActivityClasses : AppCompatActivity()  {

    private var manager: LinearLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSplashy()

        var rv: RecyclerView = findViewById(R.id.rv)
        var clases:List<Result> = apiClases().results

        rv.manejar(manager,clases,this)
        rv.addItemDecoration(DividerList(rv.context, R.drawable.divider))

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

    fun setSplashy(){
        Splashy(this)
            .setLogo(R.drawable.icon)
            .setTitle("D&D")
            .setTitleColor("#FFFFFF")
            .setSubTitle("Dungeons and Dragons")
            .setProgressColor(R.color.white)
            .setBackgroundResource(R.color.black)
            .setFullScreen(true)
            .setTime(2500)
            .show()

    }
}
