package com.example.signup

import BottomDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_my_page.*
import kotlin.properties.Delegates


class MyPageActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: MyPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)

        viewPagerAdapter= MyPageAdapter(supportFragmentManager)
        viewPagerAdapter.fragments=listOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )

        bottom_viewpager.adapter=viewPagerAdapter

        bottom_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled( position: Int, positionOffset: Float, positionOffsetPixels: Int ) {}
            override fun onPageSelected(position: Int) { sample_bottom_navi.menu.getItem(position).isChecked = true } })

            sample_bottom_navi.setOnNavigationItemSelectedListener{
                var index by Delegates.notNull<Int>()
                when(it.itemId){
                    R.id.menu_brush->index=0
                    R.id.menu_camera->index=1
                    R.id.menu_checkbox->index=2
                }
            bottom_viewpager.currentItem=index
            true
        }

        fab.setOnClickListener{
            val bottomSheet=BottomDialog()
            bottomSheet.show(supportFragmentManager,bottomSheet.tag)
        }
    }
}
