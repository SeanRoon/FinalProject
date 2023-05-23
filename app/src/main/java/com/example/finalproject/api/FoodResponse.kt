package com.example.finalproject.api

import com.squareup.moshi.Json

class FoodResponse {
    @Json(name = "branded")
    lateinit var foodItemList: List<FoodItems>
}
class FoodItems {
    @Json(name = "food_name")
    var name: String? = ""

    @Json(name = "serving_unit")
    var servingSize: String? = ""

    @Json(name = "nf_calories")
    var calories: Int? = 0

    @Json(name = "brand_name")
    var brandName: String? = ""
}