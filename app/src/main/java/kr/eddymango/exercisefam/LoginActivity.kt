package kr.eddymango.exercisefam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kr.eddymango.exercisefam.data.MysharedPreferences
import kr.eddymango.exercisefam.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef:DatabaseReference = database.getReference("User")

        binding.loginBtnRegister.setOnClickListener {
            with(binding){

                val name = loginEtName.text.toString()
                val pwd = loginEtPwd.text.toString()
                val phone = loginEtPhone.text.toString()
                MysharedPreferences.prefs.setBoolean("loignMode",true)
                MysharedPreferences.prefs.setString("name",name)

                myRef.child("User").child(name).child(name).setValue(name)
                myRef.child("User").child(name).child(pwd).setValue(pwd)
                myRef.child("User").child(name).child(phone).setValue(phone)

                Toast.makeText(this@LoginActivity,"환영합니다",Toast.LENGTH_SHORT).show()

            }
        }





    }
}