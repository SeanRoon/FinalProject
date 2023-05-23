package com.example.finalproject

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val QUERY_STRING = "?query=" +
        "&maxResults=5"

private const val BASE_URL = "https://trackapi.nutritionix.com/v2/search/instant"
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).build()

interface FoodApiService {
    @GET(QUERY_STRING)
    fun getFoodRequest(): Call<String>
}

object FoodApi {
    val foodAPI: FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}
