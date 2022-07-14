package kr.eddymango.exercisefam.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.style.TtsSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.lottie.model.content.ShapeTrimPath
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.eddymango.exercisefam.R
import kr.eddymango.exercisefam.data.MysharedPreferences.Companion.prefs
import kr.eddymango.exercisefam.databinding.FragmentHomeBinding

class HomeFragment :Fragment(){

    private var mbinding: FragmentHomeBinding? =null
    private val binding get() = mbinding!!
    private val userName = prefs.getString("name","")
    val TAG1 = "TAG"

    val database = Firebase.database("https://exercisefam-18033-default-rtdb.asia-southeast1.firebasedatabase.app")
    val myRef: DatabaseReference = database.getReference("Calendar")
    val moneyRef: DatabaseReference = database.getReference("Money")
    val weightRef: DatabaseReference = database.getReference("Weight")



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mbinding = FragmentHomeBinding.inflate(inflater, container, false)

        var day: String
        var month: String
        var year: String

        var totalMoney = "0"
        var userTotalMoney ="0"

        binding.homeTvUserName.text = "${userName}님 환영합니다."

        moneyRef.child("Total").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                totalMoney = snapshot.value.toString()
                Log.d(TAG1, "onDataChange: ${snapshot.value}")
                Log.d(TAG1, "onDataChange: ${totalMoney}")
                binding.homeTvTotalmoney.text = "현재 총 누적 금액 : ${totalMoney}원 입니다."

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })



        moneyRef.child(userName).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userTotalMoney = snapshot.value.toString()
                binding.homeTvUsertotalmoney.text = "${userName}님의 현재까지 누적 금액 : ${userTotalMoney}원 입니다."
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        binding.homeCalView.setOnDateChangeListener{ calendarView, i,i2,i3 ->
            //i = 년도 -- i2 = 월 --- i3 = 일
            day = i3.toString()
            month = (i2 +1).toString()
            year = i.toString()

            AlertDialog.Builder(activity).setView(R.layout.dialog_exer_register).show()
                .also{ alertDialog ->
                    if(alertDialog == null){
                        return@also
                    }
                    alertDialog.findViewById<TextView>(R.id.dialog_tv_name).text = "${userName}님의 ${year}년 ${month}월 ${day}일"

                    alertDialog.findViewById<Button>(R.id.dialog_btn_ok).setOnClickListener {
                        var exerCount = alertDialog.findViewById<EditText>(R.id.dialog_et_count).getText().toString()
                        var userWeight = alertDialog.findViewById<EditText>(R.id.dialog_et_weight).getText().toString()

                        Log.d(TAG1, "exercount : $exerCount")

                        myRef.child(year).child(month).child(day)
                            .child(userName).child("count").setValue(exerCount)

                        moneyRef.child(userName).setValue("${userTotalMoney.toInt() + exerCount.toInt()*10}").toString()

                        weightRef.child(userName).child(year+month+day).setValue(userWeight)
                        Log.d(TAG1, "$userTotalMoney")


                        moneyRef.child("Total").setValue("${totalMoney.toInt() + exerCount.toInt()*10}")
                        Log.d(TAG1, "onCreateView: userTotalMoney : $userTotalMoney")


                        alertDialog.dismiss()
                    }
                    alertDialog.findViewById<Button>(R.id.dialog_btn_no).setOnClickListener {

                        alertDialog.dismiss()

                    }





                    }



        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mbinding = null

    }


}