package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.finalproject.databinding.FragmentAddFoodBinding

class AddFoodFragment : Fragment() {
    private var _binding: FragmentAddFoodBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFoodBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.submitButton.setOnClickListener(){
            val food1 = binding.caloriesFood.text.toString()
            val cal = binding.caloriesNumber.text.toString()
            Toast.makeText(activity, food1 + cal, Toast.LENGTH_LONG).show()
        }
        return rootView
    }
}