package kr.eddymango.exercisefam.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.eddymango.exercisefam.data.MysharedPreferences.Companion.prefs
import kr.eddymango.exercisefam.databinding.FragmentHomeBinding

class HomeFragment :Fragment(){

    private var mbinding: FragmentHomeBinding? =null
    private val binding get() = mbinding!!
    private val userName = prefs.getString("name","")
    val TAG = "TAG"

    val database = Firebase.database("https://exercisefam-18033-default-rtdb.asia-southeast1.firebasedatabase.app")
    val myRef: DatabaseReference = database.getReference("Calendar")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mbinding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.homeCalView.setOnDateChangeListener{ calendarView, i,i2,i3 ->
            //i = 년도 -- i2 = 월 --- i3 = 일
            Toast.makeText(activity,"Selected Date : $i3/$i2/$i",Toast.LENGTH_SHORT).show()
            binding.homeBtnDbRegister.setOnClickListener {
                myRef.child("$i").child("${i2+1}").child("$i3")
                    .child("$userName").setValue("운동량 입력입니다.")
            }

        }



        return binding.root
    }

    override fun onDestroyView() {
        mbinding = null
        super.onDestroyView()

    }


}