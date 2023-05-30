package com.example.finalproject

import com.example.finalproject.api.FoodResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

private const val QUERY_STRING = "v2/search/instant?query=" +
        "&maxResults=3"

private const val BASE_URL = "https://trackapi.nutritionix.com/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()

interface FoodApiService {
    @Headers("x-app-id: ee51b338", "x-app-key: 429039b46f7cc6196b782c553491ab35")
    @GET("v2/search/instant")
    fun getFoodRequest(@Query("query") search: String, @Query("branded_food_name_only") limitCombinations: Boolean, @Query("common") includeCommon: Boolean): Call<FoodResponse>
}

object FoodApi {
    val foodAPI: FoodApiService by lazy {
        retrofit.create(FoodApiService::class.java)
    }
}
