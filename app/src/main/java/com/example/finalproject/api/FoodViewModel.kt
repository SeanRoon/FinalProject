package com.example.finalproject.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.Food
import com.example.finalproject.FoodApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodViewModel: ViewModel() {
    private val _response = MutableLiveData<List<Food>>()
    val response: LiveData<List<Food>>
        get() = _response
//    private val _request = MutableLiveData<String>()
//    val request: LiveData<String>
//        get() = _request

    fun getFoods(){
        val request = FoodApi.foodAPI.getFoodRequest()
        request.enqueue(object: Callback<FoodResponse> {
            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                Log.d("RESPONSE", "failure: " + t.message)
            }

            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                var listOfFoodsFetched = mutableListOf<Food>()

                val foodResponse: FoodResponse? = response.body()
                val foodItemsList = foodResponse?.foodItemList ?: listOf()

                for(foodItem in foodItemsList){
                    val name = foodItem.name ?: ""
                    val servingSize = foodItem.servingSize ?: ""
                    val calories = foodItem.calories
                    val brandName = foodItem.brandName ?: ""
                    val newBook = Book(title, subtitle, authors, url)
                    listOfBooksFetched.add(newBook)
                }
                _response.value = listOfBooksFetched
            }
        })
    }
}