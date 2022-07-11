package kr.eddymango.exercisefam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.eddymango.exercisefam.data.MysharedPreferences
import kr.eddymango.exercisefam.data.MysharedPreferences.Companion.prefs
import kr.eddymango.exercisefam.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val TAG = "TAG"
    val database = Firebase.database("https://exercisefam-18033-default-rtdb.asia-southeast1.firebasedatabase.app")
    val myRef:DatabaseReference = database.getReference("User")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginBtnRegister.setOnClickListener {
            with(binding){

                val name = loginEtName.text.toString()
                val pwd = loginEtPwd.text.toString()
                val phone = loginEtPhone.text.toString()
                val weight = loginEtWeight.text.toString()

                if(name ==""||pwd==""){
                    Toast.makeText(this@LoginActivity,"이름 또는 비밀번호를 입력하지 않았습니다",Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                prefs.setBoolean("loginMode",true)
                prefs.setString("name",name)
                Log.d(TAG, "loginMode : ${prefs.getBoolean("loginMode",false)}")

                myRef.child(name).child("name").setValue(name)
                myRef.child(name).child("pwd").setValue(pwd)
                myRef.child(name).child("phone").setValue(phone)
                myRef.child(name).child("weight").setValue(weight)


                Toast.makeText(this@LoginActivity,"환영합니다",Toast.LENGTH_SHORT).show()

                val intent = Intent(this@LoginActivity,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }





    }
}