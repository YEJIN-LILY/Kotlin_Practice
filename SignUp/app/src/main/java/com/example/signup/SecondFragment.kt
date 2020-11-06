package com.example.signup


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.rcv_profile.*


class SecondFragment : Fragment() {

    private lateinit var profileAdapter: ProfileAdapter
    lateinit var recyclerview1:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, null)

        profileAdapter = ProfileAdapter(requireContext())

        recyclerview1=view.findViewById(R.id.rcv_profile2!!)as RecyclerView
        recyclerview1.adapter = profileAdapter
        recyclerview1.layoutManager = LinearLayoutManager(context)
        //rcv_profile.layoutManager=GridLayoutManager(this,2)


        profileAdapter.data = mutableListOf(
            ProfileData("이름", "권예진", "2020.10.01", "이름입니다."),
            ProfileData("나이", "23", "2020.10.02", "나이입니다."),
            ProfileData("파트", "안드로이드", "2020.10.03", "파트입니다."),
            ProfileData("거주지", "서울", "2020.10.04", "거주지입니다."),
            ProfileData("취미", "여행", "2020.10.05", "취미입니다."),
            ProfileData("GitHub", "www.github.com/YEJIN-LILY", "2020.10.06", "깃헙주소입니다."),
            ProfileData("Sopt", "www.sopt.org", "2020.10.07", "동아리입니다.")
        )

        profileAdapter.notifyDataSetChanged()

        profileAdapter.setItemClickListener(object : ProfileAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val item = profileAdapter.data[position]

                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("subTitle", item.subTitle)
                intent.putExtra("date", item.date)
                intent.putExtra("contents", item.contents)
                startActivity(intent)
            }
        })

    return view
    }
}
