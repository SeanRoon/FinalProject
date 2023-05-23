package com.example.finalproject

import com.example.finalproject.api.FoodResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val QUERY_STRING = "?query=" +
        "&maxResults=5"

private const val BASE_URL = "https://trackapi.nutritionix.com/v2/search/instant"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

interface FoodApiService {
    @GET(QUERY_STRING)
    fun getFoodRequest(): Call<FoodResponse>
}

object FoodApi {
    val foodAPI: FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}
