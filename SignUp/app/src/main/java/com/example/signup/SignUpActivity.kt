package com.example.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.EditText_ID
import kotlinx.android.synthetic.main.activity_sign_up.EditText_PW
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        Button_SignUp.setOnClickListener {
            val email = EditText_ID?.text.toString()
            val password = EditText_PW?.text.toString()

            if (email.isNullOrBlank() || password.isNullOrBlank()) {
                Toast.makeText(this, "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
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
                                    Toast.makeText(this@SignUpActivity, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                                    val intent = Intent()
                                    intent.putExtra("id", email)
                                    intent.putExtra("pw", password)
                                    setResult(Activity.RESULT_OK, intent)
                                    finish()
                                }
                            }

                        }
                    )

            }
        }
    }
}
