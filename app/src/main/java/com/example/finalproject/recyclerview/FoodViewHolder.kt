package com.example.finalproject.recyclerview

import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.model.Food
import com.example.finalproject.databinding.ListItemBinding

class FoodViewHolder (val binding: ListItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentFood: Food

//    init {
//        binding.root.setOnClickListener(){
//
//        }
//    }

    fun bindFood(food: Food){
        currentFood = food
        binding.foodName.text = currentFood.foodName
        binding.calories.text = currentFood.nfCalories.toString()
    }
}