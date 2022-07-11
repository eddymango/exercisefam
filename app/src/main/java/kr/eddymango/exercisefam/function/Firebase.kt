package kr.eddymango.exercisefam.function

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Firebase {
    val database = Firebase.database("https://exercisefam-18033-default-rtdb.asia-southeast1.firebasedatabase.app")
    val myRef: DatabaseReference = database.getReference("Calendar")






}