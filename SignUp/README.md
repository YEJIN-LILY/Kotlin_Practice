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

# 3주차 과제      

🍀 **필수과제: Fragment(2020.11.06 완료)**   
* 실습 화면   

<img src="https://user-images.githubusercontent.com/57944153/98362610-e415ec00-2070-11eb-9f0f-a5b4566dec5c.png" width="200" height="300"/>|<img src="https://user-images.githubusercontent.com/57944153/98362663-ff80f700-2070-11eb-9a90-c897223a65e9.png" width="200" height="300"/>|<img src="https://user-images.githubusercontent.com/57944153/98362691-0ad42280-2071-11eb-9b82-c34d997aa7e3.png" width="200" height="300"/>|<img src="https://user-images.githubusercontent.com/57944153/98362773-28a18780-2071-11eb-9564-134c5459f4db.png" width="200" height="300"/>   

- 구현코드
   + FirstFragment.kt      
   ```
   class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    var name="default"
    private lateinit var viewpagerAdapter: SmallViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_first, container, false)

        viewpagerAdapter= SmallViewAdapter(childFragmentManager)
        viewpagerAdapter.fragments=listOf(
            SmallFirstFragment(),
            SmallSecondFragment()
        )

        view.tab_viewpager.adapter=viewpagerAdapter

        view.profile_tab.setupWithViewPager(view.tab_viewpager)
        view.profile_tab.apply{
            getTabAt(0)?.text="INFO"
            getTabAt(1)?.text="OTHER"
        }
        return view
    }
  }
  ```
 
   + SecondFragment.kt   
   ```
   class SecondFragment : Fragment() {

    private lateinit var profileAdapter: ProfileAdapter
    lateinit var recyclerview1:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, null)

        profileAdapter = ProfileAdapter(requireContext())

        recyclerview1=view.findViewById(R.id.rcv_profile2!!)as RecyclerView
        recyclerview1.adapter = profileAdapter
        recyclerview1.layoutManager = LinearLayoutManager(context)
        //rcv_profile.layoutManager=GridLayoutManager(this,2)


        profileAdapter.data = mutableListOf(
            ProfileData("이름", "권예진", "2020.10.01", "이름입니다."),
            ProfileData("나이", "23", "2020.10.02", "나이입니다."),
            ProfileData("파트", "안드로이드", "2020.10.03", "파트입니다."),
            ProfileData("거주지", "서울", "2020.10.04", "거주지입니다."),
            ProfileData("취미", "여행", "2020.10.05", "취미입니다."),
            ProfileData("GitHub", "www.github.com/YEJIN-LILY", "2020.10.06", "깃헙주소입니다."),
            ProfileData("Sopt", "www.sopt.org", "2020.10.07", "동아리입니다.")
        )

        profileAdapter.notifyDataSetChanged()

        profileAdapter.setItemClickListener(object : ProfileAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val item = profileAdapter.data[position]

                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("subTitle", item.subTitle)
                intent.putExtra("date", item.date)
                intent.putExtra("contents", item.contents)
                startActivity(intent)
            }
        })

    return view
    }
  }
  ```   
     + ThirdFragment.kt   
     ```
     class ThirdFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val preference=context?.getSharedPreferences("temp",Context.MODE_PRIVATE)
        val view=inflater.inflate(R.layout.fragment_third, container, false)
        val editor:SharedPreferences.Editor=preference!!.edit()

        view.btn_logout.setOnClickListener{
            val builder= AlertDialog.Builder(ContextThemeWrapper(context,R.style.Theme_AppCompat_DayNight_Dialog))
            builder.setTitle("로그아웃하시겠습니까?")
            builder.setMessage(" ")
            builder.setPositiveButton("확인"){yes,id->
                editor.remove("id")
                editor.remove("pw")
                editor.clear()
                editor.apply()

                Toast.makeText(context,"로그아웃되었습니다.",Toast.LENGTH_SHORT).show()
                val intent= Intent(context,MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
            builder.setNegativeButton("취소"){no,id->

            }
            builder.show()
        }
        return view
    }
  }
  ```
