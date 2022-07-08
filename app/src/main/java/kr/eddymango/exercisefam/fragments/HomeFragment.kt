package kr.eddymango.exercisefam.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.eddymango.exercisefam.databinding.FragmentHomeBinding

class HomeFragment :Fragment(){

    private var mbinding: FragmentHomeBinding? =null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container,false)
        mbinding = binding


        return mbinding?.root
    }

    override fun onDestroyView() {
        mbinding = null
        super.onDestroyView()

    }


}