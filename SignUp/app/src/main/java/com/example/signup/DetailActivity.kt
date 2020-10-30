package com.example.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        TextView_title.setText(intent.getStringExtra("title").toString())
        TextView_subTitle.setText(intent.getStringExtra("subTitle").toString())
        TextView_date.setText(intent.getStringExtra("date").toString())
        TextView_contents.setText(intent.getStringExtra("contents").toString())
    }
}
