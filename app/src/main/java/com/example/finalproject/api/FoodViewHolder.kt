package com.example.finalproject.api

import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Food
import com.example.finalproject.databinding.ListItemBinding

class FoodViewHolder (val binding: ListItemBinding):
    RecyclerView.ViewHolder(binding.root) {

    private lateinit var currentFood: Food

    fun bindBook(food: Food){
        currentFood = food
//        binding.Title.text = currentBook.title
//        binding.Subtitle.text = currentBook.subtitle
//        binding.author.text = currentBook.authors
    }
}