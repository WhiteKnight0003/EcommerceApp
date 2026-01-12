package com.example.ecommerceapp.model

data class Product(
    val id: String ="",
    val name: String ="",
    val price: Double =0.0,
    val imageUrl: String = "",
    val description: String = "",
    val categoryId: String = "",

)
