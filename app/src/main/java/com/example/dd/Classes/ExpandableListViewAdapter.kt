package com.example.dd.com.example.dd.Classes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.dd.Classes.From
import android.graphics.Typeface
import com.example.dd.R
import android.view.View
import android.content.DialogInterface
import android.app.Activity

class ExpandableListViewAdapter(private val listaNom:List<String>, private val context: Activity, private val coleccion:List<List<String>>) : BaseExpandableListAdapter() {

    override fun getChild(groupPosition: Int, childPosition: Int): Any {

        return coleccion[groupPosition]!![childPosition]
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }


    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup ): View {

        var convertView = convertView
        val child = getChild(groupPosition, childPosition) as String
        val inflater = context.layoutInflater

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child, null)
        }

        val item = convertView!!.findViewById<View>(R.id.txtChild) as TextView
        item.text = child
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {

        return coleccion[groupPosition]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return listaNom[groupPosition]
    }

    override fun getGroupCount(): Int {
        return coleccion.size
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup
    ): View {
        var convertView = convertView
        val stringName = getGroup(groupPosition) as String
        if (convertView == null) {
            val infalInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = infalInflater.inflate(R.layout.group, null)
        }
        val item = convertView!!.findViewById<View>(R.id.txtGroup) as? TextView
        item?.setTypeface(null, Typeface.BOLD)
        item?.text = stringName
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}