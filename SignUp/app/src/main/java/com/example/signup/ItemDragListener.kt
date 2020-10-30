package com.example.signup

import androidx.recyclerview.widget.RecyclerView

interface ItemDragListener{
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}