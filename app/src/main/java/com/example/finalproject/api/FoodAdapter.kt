package com.example.finalproject.api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.Food
import com.example.finalproject.databinding.ListItemBinding

class FoodAdapter (val foodList: List<Food>) : RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val currentSnap = foodList[position]
//        holder.bindBook(currentSnap)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}