package kr.eddymango.exercisefam.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.eddymango.exercisefam.databinding.FragmentFamilyBinding
import kr.eddymango.exercisefam.databinding.FragmentInfoBinding

class InfoFragment :Fragment(){

    private var mbinding: FragmentInfoBinding? =null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInfoBinding.inflate(inflater, container,false)
        mbinding = binding


        return mbinding?.root
    }

    override fun onDestroyView() {
        mbinding = null
        super.onDestroyView()

    }


}