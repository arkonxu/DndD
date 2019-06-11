package com.example.dd.com.example.dd.Classes

import android.support.v7.widget.RecyclerView
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.support.v7.widget.RecyclerView.State
import android.util.Log
import android.view.View
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.DividerItemDecoration
import android.R.attr.divider
import android.support.v4.content.ContextCompat
import java.security.AccessController.getContext


class DividerList(context: Context, drawableXML:Int) : ItemDecoration() {
    private var mDivider: Drawable? = null
    private var mOrientation: Int = 1
    private val mBounds = Rect()

    init {
        val a = context.obtainStyledAttributes(ATTRS)
        this.mDivider = a.getDrawable(0)
        if (this.mDivider == null) {
            Log.w(
                "DividerItem",
                "@android:attr/listDivider was not set in the theme used for this DividerItemDecoration. Please set that attribute all call setDrawable()"
            )
        }

        val colorDivider = ContextCompat.getDrawable(context, drawableXML)
        this.setDrawable(colorDivider!!)

        a.recycle()
        this.setOrientation(VERTICAL)
    }

    fun setOrientation(orientation: Int) {
        if (orientation != 0 && orientation != 1) {
            throw IllegalArgumentException("Invalid orientation. It should be either HORIZONTAL or VERTICAL")
        } else {
            this.mOrientation = orientation
        }
    }

    fun setDrawable(drawable: Drawable) {
        if (drawable == null) {
            throw IllegalArgumentException("Drawable cannot be null.")
        } else {
            this.mDivider = drawable
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {
        if (parent.layoutManager != null && this.mDivider != null) {
            if (this.mOrientation == 1) {
                this.drawVertical(c, parent)
            } else {
                this.drawHorizontal(c, parent)
            }

        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val left: Int
        val right: Int
        if (parent.clipToPadding) {
            left = parent.paddingLeft
            right = parent.width - parent.paddingRight
            canvas.clipRect(left, parent.paddingTop, right, parent.height - parent.paddingBottom)
        } else {
            left = 0
            right = parent.width
        }

        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.getDecoratedBoundsWithMargins(child, this.mBounds)
            val bottom = this.mBounds.bottom + Math.round(child.translationY)
            val top = bottom - this.mDivider!!.intrinsicHeight
            this.mDivider!!.setBounds(left, top, right, bottom)
            this.mDivider!!.draw(canvas)
        }

        canvas.restore()
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        canvas.save()
        val top: Int
        val bottom: Int
        if (parent.clipToPadding) {
            top = parent.paddingTop
            bottom = parent.height - parent.paddingBottom
            canvas.clipRect(parent.paddingLeft, top, parent.width - parent.paddingRight, bottom)
            canvas.drawColor(Color.RED)
        } else {
            top = 0
            bottom = parent.height
            canvas.drawColor(Color.RED)
        }

        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            parent.layoutManager!!.getDecoratedBoundsWithMargins(child, this.mBounds)
            val right = this.mBounds.right + Math.round(child.translationX)
            val left = right - this.mDivider!!.intrinsicWidth
            this.mDivider!!.setBounds(left, top, right, bottom)
            this.mDivider!!.draw(canvas)
        }

        canvas.restore()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        if (this.mDivider == null) {
            outRect.set(0, 0, 0, 0)
        } else {
            if (this.mOrientation == 1) {
                outRect.set(0, 0, 0, this.mDivider!!.intrinsicHeight)
            } else {
                outRect.set(0, 0, this.mDivider!!.intrinsicWidth, 0)
            }

        }
    }

    companion object {
        val HORIZONTAL = 0
        val VERTICAL = 1
        private val TAG = "DividerItem"
        private val ATTRS = intArrayOf(16843284)
    }
}
