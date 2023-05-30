package com.example.finalproject.api

import com.squareup.moshi.Json

class FoodResponse {
    @Json(name = "branded")
    lateinit var foodItemList: List<FoodItems>
}
class FoodItems {
    @Json(name = "food_name")
    var foodName: String? = ""

    @Json(name = "serving_unit")
    var servingUnit: String? = ""

    @Json(name = "nf_calories")
    var nfCalories: Int? = 0

    @Json(name = "brand_name")
    var brandName: String? = ""
}