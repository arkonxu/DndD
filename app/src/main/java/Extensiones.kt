package com.example.dd

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.example.dd.Classes.Adapter
import com.example.dd.Classes.Result

fun RecyclerView.manejar(manager:LinearLayoutManager, lista:List<Result>, actividad:Activity){
    this.layoutManager = manager
    this.adapter = Adapter(lista,actividad)
}

fun ImageView.setDrawable(nombreImagen:String){
    this.setImageResource(getResources().getIdentifier(nombreImagen, "drawable", "com.example.dd"))
}