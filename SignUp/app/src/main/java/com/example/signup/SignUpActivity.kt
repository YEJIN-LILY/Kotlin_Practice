package com.example.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        Button_SignUp.setOnClickListener{
            val name=EditText_name?.text.toString()
            val id=EditText_ID?.text.toString()
            val pw=EditText_PW?.text.toString()

            val len_name=name.length
            val len_id=id.length
            val len_pw=pw.length

            if(len_name>0&&len_id>0&&len_pw>0) {
                Toast.makeText(this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                val intent=Intent()
                intent.putExtra("id",id)
                intent.putExtra("pw",pw)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
            else
                Toast.makeText(this,"빈칸이 있습니다.",Toast.LENGTH_SHORT).show()
        }
    }

}
