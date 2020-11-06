package com.example.signup

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_third.view.*


class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val preference=context?.getSharedPreferences("temp",Context.MODE_PRIVATE)
        val view=inflater.inflate(R.layout.fragment_third, container, false)
        val editor:SharedPreferences.Editor=preference!!.edit()

        view.btn_logout.setOnClickListener{
            val builder= AlertDialog.Builder(ContextThemeWrapper(context,R.style.Theme_AppCompat_DayNight_Dialog))
            builder.setTitle("로그아웃하시겠습니까?")
            builder.setMessage(" ")
            builder.setPositiveButton("확인"){yes,id->
                editor.remove("id")
                editor.remove("pw")
                editor.clear()
                editor.apply()

                Toast.makeText(context,"로그아웃되었습니다.",Toast.LENGTH_SHORT).show()
                val intent= Intent(context,MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            builder.setNegativeButton("취소"){no,id->

            }
            builder.show()
        }
        return view
    }
}
