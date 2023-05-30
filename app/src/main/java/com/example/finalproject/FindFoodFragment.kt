package com.example.finalproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.finalproject.recyclerview.FoodAdapter
import com.example.finalproject.databinding.FragmentFindFoodBinding
import com.example.finalproject.model.FoodViewModel

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
//            val myAdapter = FoodAdapter(foodList)
//            binding.recyclerView.adapter = myAdapter
            Log.d("Response", foodList[0].foodName)
            Log.d("Response", foodList[0].servingUnit)
            Log.d("Response", foodList[0].nfCalories.toString())
            Log.d("Response", foodList[0].brandName)
        })
//        viewModel.getFoods()
        return rootView
    }
}