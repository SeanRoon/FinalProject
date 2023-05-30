package com.example.finalproject.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject.FoodApi
import com.example.finalproject.api.FoodResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodViewModel: ViewModel() {
    private val _response = MutableLiveData<List<Food>>()
    val response: LiveData<List<Food>>
        get() = _response
    private val _request = MutableLiveData<String>()
    val request: LiveData<String>
        get() = _request

    fun getFoods(userSearch: String){
        val request = FoodApi.foodAPI.getFoodRequest(userSearch,true, true)
        request.enqueue(object: Callback<FoodResponse> {
            override fun onFailure(call: Call<FoodResponse>, t: Throwable) {
                Log.d("RESPONSE", "failure: " + t.message)
            }

            override fun onResponse(call: Call<FoodResponse>, response: Response<FoodResponse>) {
                var listOfFoodsFetched = mutableListOf<Food>()

                val foodResponse: FoodResponse? = response.body()
                val foodItemsList = foodResponse?.foodItemList ?: listOf()

                for(foodItem in foodItemsList){
                    val foodName = foodItem.foodName ?: ""
                    val servingUnit = foodItem.servingUnit ?: ""
                    val nfCalories = foodItem.nfCalories ?: 0
                    val brandName = foodItem.brandName ?: ""
                    val newFood = Food(foodName, servingUnit, nfCalories, brandName)
                    listOfFoodsFetched.add(newFood)
                }
                _response.value = listOfFoodsFetched
            }
        })
    }
}