# 1주차 과제

🍀 필수과제: SignUpActivity 만들기(2020.10.12 완료)
  * 실습화면
  <img src="https://user-images.githubusercontent.com/57944153/96099862-af4dc380-0f0e-11eb-93e6-817f0825ed16.png" width="200" height="300"/>
  <img src="https://user-images.githubusercontent.com/57944153/96100851-c640e580-0f0f-11eb-9693-c22315bebf38.png" width="200" height="300"/>
  <img src="https://user-images.githubusercontent.com/57944153/96100925-d953b580-0f0f-11eb-9b76-178f565ad86e.png" width="200" height="300"/>
  
  - 구현코드
  
    - 토스트 메시지 출력
    ```
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
       ```
