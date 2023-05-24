package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.finalproject.api.FoodAdapter
import com.example.finalproject.api.FoodViewModel
import com.example.finalproject.databinding.FragmentAddFoodBinding

class AddFoodFragment : Fragment() {
    private var _binding: FragmentAddFoodBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FoodViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFoodBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.searchButton.setOnClickListener(){
            rootView.findNavController().navigate(R.id.action_addFoodFragment_to_findFoodFragment)
        }
        val userSearch = binding.foodRequest.text
        return rootView
    }
}