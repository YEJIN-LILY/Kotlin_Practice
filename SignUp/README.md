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

# 2주차 과제

🍀 **필수과제: RecyclerView 상세화면 이동(2020.10.27 완료)**
* 실습 화면   
<img src="https://user-images.githubusercontent.com/57944153/97669019-8ad51800-1ac6-11eb-983d-fd7a0c57f069.png" width="200" height="300"/>|<img src="https://user-images.githubusercontent.com/57944153/97669111-beb03d80-1ac6-11eb-9a02-598dc38f4682.png" width="200" height="300"/>   

- 구현코드   
  + rcv_ProfileActivity.kt   
  ```
  profileAdapter.setItemClickListener(object:ProfileAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                val item=profileAdapter.data[position]

                val intent = Intent(v.context,DetailActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("subTitle", item.subTitle)
                intent.putExtra("date", item.date)
                intent.putExtra("contents", item.contents)
                startActivity(intent)
            }
        })
   ```   
   + ProfileAdapter.kt
   ```
   override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val item=data[position]
        holder.onBind(item)

        holder.itemView.setOnClickListener{
            itemClickListener.onClick(it,position)
        }
    }
    ```   
    + DetailActivity.kt   
    ```
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
   ```   

