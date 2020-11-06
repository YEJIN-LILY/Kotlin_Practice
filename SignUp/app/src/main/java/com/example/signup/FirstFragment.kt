package com.example.signup

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*


class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var name="default"
    private lateinit var viewpagerAdapter: SmallViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_first, container, false)

        viewpagerAdapter= SmallViewAdapter(childFragmentManager)
        viewpagerAdapter.fragments=listOf(
            SmallFirstFragment(),
            SmallSecondFragment()
        )

        view.tab_viewpager.adapter=viewpagerAdapter

        view.profile_tab.setupWithViewPager(view.tab_viewpager)
        view.profile_tab.apply{
            getTabAt(0)?.text="INFO"
            getTabAt(1)?.text="OTHER"
        }
        return view
    }

}
