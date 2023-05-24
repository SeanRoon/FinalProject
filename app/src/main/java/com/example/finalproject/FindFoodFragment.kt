package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.finalproject.api.FoodAdapter
import com.example.finalproject.api.FoodViewModel
import com.example.finalproject.databinding.FragmentAddFoodBinding
import com.example.finalproject.databinding.FragmentFindFoodBinding

class FindFoodFragment : Fragment() {
    private var _binding: FragmentFindFoodBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FoodViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFindFoodBinding.inflate(inflater, container, false)
        val rootView = binding.root
        viewModel.response.observe(viewLifecycleOwner, Observer { foodList ->
            val myAdapter = FoodAdapter(foodList)
            binding.recyclerView.adapter = myAdapter
        })
        viewModel.getFoods()
        return rootView
    }
}