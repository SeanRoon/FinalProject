package com.example.finalproject.api

import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Food
import com.example.finalproject.databinding.ListItemBinding

class FoodViewHolder (val binding: ListItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentFood: Food

    fun bindBook(food: Food){
        currentFood = food
        binding.foodName.text = currentFood.name
        binding.calories.text = currentFood.calories.toString()
    }
}