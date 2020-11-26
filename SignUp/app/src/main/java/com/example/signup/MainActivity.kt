package com.example.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TextView_SignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 100)
        }

        if (MySharedPreferences.getUserId(this).isNullOrBlank() || MySharedPreferences.getUserPass(
                this
            ).isNullOrBlank()
        ) { //자동로그인x
            Login()
        } else { // SharedPreferences 안에 값이 저장되어 있을 때 -> rcv_ProfileActivity 이동
            Toast.makeText(
                this,
                "${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.",
                Toast.LENGTH_SHORT
            ).show()
            //val intent = Intent(this, rcv_ProfileActivity::class.java)
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    EditText_ID.setText(data!!.getStringExtra("id").toString())
                    EditText_PW.setText(data!!.getStringExtra("pw").toString())
                }
            }
        }
    }

    fun Login() {
        Button_LogIn.setOnClickListener {
            if (EditText_ID.text.isNullOrBlank() || EditText_PW.text.isNullOrBlank()) { //빈칸이 있을 경우
                Toast.makeText(this@MainActivity, "아이디와 비밀번호를 모두 입력하세요", Toast.LENGTH_SHORT).show()
            } else { //처음 로그인
                val email = EditText_ID.text.toString()
                val password = EditText_PW.text.toString()

                ServiceImpl.service.postLogin(
                    RequestLoginData(
                        email = email,
                        password = password
                    )
                )
                    .enqueue(
                        object : Callback<ResponseLoginData> {
                            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                                Log.d("통신 실패", t.toString())
                            }

                            override fun onResponse(
                                call: Call<ResponseLoginData>,
                                response: Response<ResponseLoginData>
                            ) {
                                if (response.isSuccessful) {
                                    Log.d("login", response.body().toString())
                                    val intent =
                                        Intent(this@MainActivity, MyPageActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                            }

                        }
                    )
            }
        }

    }
}
