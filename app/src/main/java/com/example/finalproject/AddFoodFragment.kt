package com.example.finalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.finalproject.databinding.FragmentAddFoodBinding
import com.example.finalproject.model.FoodViewModel
import com.example.finalproject.recyclerview.FoodAdapter

class AddFoodFragment : Fragment() {
    private var _binding: FragmentAddFoodBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FoodViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddFoodBinding.inflate(inflater, container, false)
        val rootView = binding.root
        binding.searchButton.setOnClickListener(){
            val userSearch = binding.foodRequest.text.toString()
            viewModel.getFoods(userSearch)
            viewModel.response.observe(viewLifecycleOwner, Observer { foodList ->
                val myAdapter = FoodAdapter(foodList)
                binding.recyclerView.adapter = myAdapter
            })
        }

        return rootView
    }
}