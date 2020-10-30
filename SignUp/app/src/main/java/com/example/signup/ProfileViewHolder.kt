package com.example.signup

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){


        private val title: TextView =itemView.findViewById(R.id.item_title) //view가 될 것임
        private val subTitle:TextView=itemView.findViewById(R.id.item_subtitle)

        fun onBind(data:ProfileData){
                title.text=data.title
                subTitle.text=data.subTitle
        }
}