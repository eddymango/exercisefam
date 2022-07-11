package kr.eddymango.exercisefam.fragments

import android.app.AlertDialog
import android.os.Bundle
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
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.eddymango.exercisefam.R
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

        var day:String
        var month:String
        var year:String





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
                        Log.d(TAG, "exercount : $exerCount")

                        myRef.child(year).child(month).child(day)
                            .child(userName).child("count").setValue(exerCount)

                        myRef.child(year).child(month).child(day)
                            .child(userName).child("money").setValue("${exerCount.toInt()*10}")

                        alertDialog.dismiss()

                    }




                }



        }



        return binding.root
    }

    override fun onDestroyView() {
        mbinding = null
        super.onDestroyView()

    }


}