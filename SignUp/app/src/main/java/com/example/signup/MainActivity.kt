package com.example.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TextView_SignUp.setOnClickListener{
            val intent= Intent(this,SignUpActivity::class.java)
            startActivityForResult(intent,100)
        }

        if(MySharedPreferences.getUserId(this).isNullOrBlank() || MySharedPreferences.getUserPass(this).isNullOrBlank()) { //자동로그인x
            Login()
        }
        else { // SharedPreferences 안에 값이 저장되어 있을 때 -> HomeActivity로 이동
            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK){
            when(requestCode){
                100->{
                    EditText_ID.setText(data!!.getStringExtra("id").toString())
                    EditText_PW.setText(data!!.getStringExtra("pw").toString())
                }
            }
        }
    }

    fun Login() {
        Button_LogIn.setOnClickListener{
            if(EditText_ID.text.isNullOrBlank() || EditText_PW.text.isNullOrBlank()) { //빈칸이 있을 경우
                Toast.makeText(this, "아이디와 비밀번호를 모두 입력하세요", Toast.LENGTH_SHORT).show()
            }
            else { //처음 로그인
                MySharedPreferences.setUserId(this, EditText_ID.text.toString())
                MySharedPreferences.setUserPass(this, EditText_PW.text.toString())
                Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
