package com.example.signup

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProfileAdapter(private val context: Context): RecyclerView.Adapter<ProfileViewHolder>(){
    var data= mutableListOf<ProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.item_list,parent,false)

        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val item=data[position]
        holder.onBind(item)

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it,position)
        }
    }

    interface OnItemClickListener{
        fun onClick(v:View,position:Int)
    }
    private lateinit var itemClickListener:OnItemClickListener

    fun setItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener=itemClickListener
    }


}