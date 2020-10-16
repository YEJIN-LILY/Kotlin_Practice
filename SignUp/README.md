# 1주차 과제

🍀 **필수과제: SignUpActivity 만들기(2020.10.12 완료)**
  * 실습화면   
  <img src="https://user-images.githubusercontent.com/57944153/96099862-af4dc380-0f0e-11eb-93e6-817f0825ed16.png" width="200" height="300"/>|<img src="https://user-images.githubusercontent.com/57944153/96100851-c640e580-0f0f-11eb-9693-c22315bebf38.png" width="200" height="300"/>|<img src="https://user-images.githubusercontent.com/57944153/96100925-d953b580-0f0f-11eb-9b76-178f565ad86e.png" width="200" height="300"/>
  
  - 구현코드
  
    - 토스트 메시지 출력
    ```
    class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        Button_SignUp.setOnClickListener{
            val id = EditText_ID?.text.toString()
            val pw = EditText_PW?.text.toString()

            if (id.isNullOrBlank() || pw.isNullOrBlank()) {
                Toast.makeText(this, "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show()
                val intent = Intent()
                intent.putExtra("id", id)
                intent.putExtra("pw", pw)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
             }
          }
        }
       ```   
       
🍀 **성장과제1: 화면이동+@(2020.10.13 완료)**
 * 실습 화면   
 <img src="https://user-images.githubusercontent.com/57944153/96100925-d953b580-0f0f-11eb-9b76-178f565ad86e.png" width="200" height="300"/>|<img src="https://user-images.githubusercontent.com/57944153/96222798-dc11e180-0fc7-11eb-92b3-369349c353c8.png" width="200" height="300"/>
 
 - 구현코드
   - onActivityResult
   ```
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
    ```
    
🍀 **성장과제2: 자동 로그인(2020.10.16 완료)**
* 실습 화면   
<img src="https://user-images.githubusercontent.com/57944153/96227956-d7e9c200-0fcf-11eb-9122-efabe5c7421e.png" width="200" height="300"/>   

- 구현코드
  + MySharedPreferences.kt
  ```
  object MySharedPreferences{
    private val MY_ACCOUNT : String = "account"

    fun setUserId(context: Context, input: String) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_ID", input)
        editor.commit()
    }

    fun getUserId(context: Context): String {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString("MY_ID", "").toString()
    }

    fun setUserPass(context: Context, input: String) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_PASS", input)
        editor.commit()
    }

    fun getUserPass(context: Context): String {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        return prefs.getString("MY_PASS", "").toString()
    }

    fun clearUser(context: Context) {
        val prefs : SharedPreferences = context.getSharedPreferences(MY_ACCOUNT, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.commit()
    }
   }
  ```
  + MainActivity using MySharedPreferences
  ```
  if(MySharedPreferences.getUserId(this).isNullOrBlank() || MySharedPreferences.getUserPass(this).isNullOrBlank()) { //자동로그인x
            Login()
        }
        else { // SharedPreferences 안에 값이 저장되어 있을 때 -> HomeActivity로 이동
            Toast.makeText(this, "${MySharedPreferences.getUserId(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
  ```
