package com.example.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.rcv_profile.*

class rcv_ProfileActivity: AppCompatActivity(){
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rcv_profile)

        profileAdapter= ProfileAdapter(this)

        rcv_profile.adapter=profileAdapter
        rcv_profile.layoutManager=LinearLayoutManager(this)
        //rcv_profile.layoutManager=GridLayoutManager(this,2)


        profileAdapter.data=mutableListOf(
            ProfileData("이름","권예진","2020.10.01","이름입니다.") ,
            ProfileData("나이","23","2020.10.02","나이입니다."),
            ProfileData("파트","안드로이드","2020.10.03","파트입니다.") ,
            ProfileData("거주지","서울","2020.10.04","거주지입니다.") ,
            ProfileData("취미","여행","2020.10.05","취미입니다.") ,
            ProfileData("GitHub","www.github.com/YEJIN-LILY","2020.10.06","깃헙주소입니다.") ,
            ProfileData("Sopt","www.sopt.org","2020.10.07","동아리입니다.")
        )

        profileAdapter.notifyDataSetChanged()

        profileAdapter.setItemClickListener(object:ProfileAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val item=profileAdapter.data[position]

                val intent = Intent(v.context,DetailActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("subTitle", item.subTitle)
                intent.putExtra("date", item.date)
                intent.putExtra("contents", item.contents)
                startActivity(intent)
            }
        })
    }
}