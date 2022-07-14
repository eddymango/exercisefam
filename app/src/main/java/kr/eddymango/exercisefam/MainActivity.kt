package kr.eddymango.exercisefam

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kr.eddymango.exercisefam.data.MysharedPreferences.Companion.prefs
import kr.eddymango.exercisefam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val TAG:String = "TAG"
    private lateinit var binding: ActivityMainBinding
    private val splashDuration = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //딜레이
        Handler().postDelayed({
            /*
            sharedPreference 값 비교
            intent
                -> 최초 로그인 O : LoginActivity
                -> 최초 로그인 X : HomeActivity
             */

            var loginMode = prefs.getBoolean("loginMode",false)
            Log.d(TAG, "loginMode value : $loginMode" )
            //최초 로그인 x
            if(loginMode){
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            // 최초 로그인 o
            else{
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)

                finish()
            }

        }, splashDuration)


    }


}