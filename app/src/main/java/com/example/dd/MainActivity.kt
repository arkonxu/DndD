package com.example.dd

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.net.URL;
import com.google.gson.GsonBuilder;
import android.widget.TextView
import android.view.View
import android.os.StrictMode



class MainActivity : AppCompatActivity()  {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiCall()
    }

    private fun apiCall(): Result {

            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val builder = GsonBuilder()
            val gson = builder.create()
            val json = URL("http://dnd5eapi.co/api/classes").readText()
            var api = gson.fromJson(json, Result::class.java)
            return api

    }

    fun cambiaText(v:View){
        v.setOnClickListener {
            var tv: TextView = findViewById(R.id.user)
            tv.text = apiCall().name
        }
    }

}
