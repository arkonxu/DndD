package com.example.dd

import android.app.Activity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.dd.Classes.Adapter
import com.example.dd.Classes.MainActivityClasses
import com.example.dd.Classes.Result
import org.junit.internal.Classes
import kotlin.math.roundToInt

fun RecyclerView.manejar(manager:LinearLayoutManager, lista:List<Result>, actividad:Activity){
    this.layoutManager = manager
    this.adapter = Adapter(lista,actividad)
}