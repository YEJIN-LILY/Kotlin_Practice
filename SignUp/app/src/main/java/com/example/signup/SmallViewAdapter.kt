package com.example.signup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class SmallViewAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    var fragments = listOf<Fragment>()
    override fun getItem(position: Int): Fragment = when(position){
        0->SmallFirstFragment()
        1->SmallSecondFragment()
        else->throw IllegalStateException("Unexpected position $position")
    }
    override fun getCount(): Int = fragments.size
}