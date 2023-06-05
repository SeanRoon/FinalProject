package com.example.finalproject.recyclerview

import android.annotation.SuppressLint
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.User
import com.example.finalproject.model.Food
import com.example.finalproject.databinding.ListItemBinding
import com.example.finalproject.model.FoodViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FoodViewHolder (val binding: ListItemBinding):
    RecyclerView.ViewHolder(binding.root) {
    private lateinit var currentFood: Food
    private lateinit var viewModel: FoodViewModel
    private lateinit var user: FirebaseUser
    lateinit var dbRef: DatabaseReference
    private lateinit var auth: FirebaseAuth
    init {
        binding.root.setOnClickListener(){
            viewModel = FoodViewModel()
            val cals = binding.calories.text.toString().substring(10, binding.calories.text.length).toInt()
            auth = FirebaseAuth.getInstance()
            user = auth.currentUser!!
            dbRef = FirebaseDatabase.getInstance().getReference("Users")
            val userEmail = user.email?.substring(0, user.email?.length?.minus(10) ?: 0)
            dbRef.child(userEmail!!).get().addOnSuccessListener {
                val caloriesEaten = it.child("caloriesEaten").value.toString().toInt()
                val totalCalories = it.child("totalCalories").value.toString().toInt()
                val userInfo = mapOf<String, Int>("totalCalories" to totalCalories, "caloriesEaten" to caloriesEaten + cals)
                dbRef.child(userEmail).updateChildren(userInfo)
            }
            binding.root.findNavController().navigate(R.id.action_addFoodFragment_to_homeScreenFragment)
        }
    }

    @SuppressLint("SetTextI18n")
    fun bindFood(food: Food){
        currentFood = food
        binding.foodName.text = currentFood.foodName
        binding.calories.text = "Calories: " + currentFood.nfCalories.toString()
        binding.servingSize.text = "Serving Size: " + currentFood.servingUnit
    }
}