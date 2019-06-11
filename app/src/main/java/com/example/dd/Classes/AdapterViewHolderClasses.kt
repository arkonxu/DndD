package com.example.dd.Classes

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dd.R
import com.example.dd.com.example.dd.Classes.ClassesDetails
import com.example.dd.setDrawable
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.details_classes.view.*
import kotlinx.android.synthetic.main.recyclerview_adapter.view.*
import java.io.Serializable

class Adapter  (val classesList: List<Result>, var actividad: Activity) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter, parent, false)
        var view = ViewHolder(v)
        v.setOnClickListener{

            var clase = classesList[view.adapterPosition]
            //var p1=Pair.create<View,String>(it.tv,"iv")
            //var opcion= makeSceneTransitionAnimation(actividad,p1)
            // ActivityCompat.startActivity(actividad, it.context.intentFor<PerfilUsuario>("user",user)
            //)
            var intent = Intent(it.context, ClassesDetails::class.java)
            intent.putExtra("CLASE", clase as Serializable)
            it.context.startActivity(intent)

        }
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){holder.rellenar(classesList[position])}

    override fun getItemCount():Int{return classesList.size}

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun rellenar(item : Result) {
            itemView.nc.text=item.name
            itemView.imagenClase.clipToOutline=true
            itemView.imagenClase.setDrawable(item.name.decapitalize())

//            itemView.imagenClase.setImageResource(itemView.getResources().getIdentifier("barbarian", "drawable", "com.example.dd"))
//            itemView.tv2.text=item.edad.toString()
//            Picasso.get().load(item.linkim).into(itemView.iv)
        }
    }
}