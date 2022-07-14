package kr.eddymango.exercisefam.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kr.eddymango.exercisefam.data.MysharedPreferences
import kr.eddymango.exercisefam.databinding.FragmentFamilyBinding
import kr.eddymango.exercisefam.databinding.FragmentInfoBinding

class InfoFragment :Fragment(){

    private var _binding: FragmentInfoBinding? =null
    private val binding get() = _binding!!

    private val userName = MysharedPreferences.prefs.getString("name","")

    val database = Firebase.database("https://exercisefam-18033-default-rtdb.asia-southeast1.firebasedatabase.app")
    val weightRef: DatabaseReference = database.getReference("Weight")


    val dataList = mutableListOf<String>()
    val weightList = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container,false)
        val lineChart: LineChart =binding.infoLineChart

        val entry = ArrayList<Entry>()

        weightRef.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.child(userName)
                for (tmpWeight in data.children){
                    dataList.add(tmpWeight.key.toString())
                    weightList.add(tmpWeight.value.toString())
                    //Log.d("MyTag", "$tmpWeight")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
//
//        for (i in dataList.indices){
//            entry.add(Entry())
//        }





        lineChart.run {

        }



        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}