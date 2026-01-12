package com.example.ecommerceapp.screen.navigator

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Cart : Screens("cart")
    object ProductDetails : Screens("product_details/{productId}"){
        fun createRoute(productId: String) = "product_details/${productId}"
    }
    object ProductsList : Screens("product_list/{categoryId}"){
        fun createRoute(categoryId: String) = "product_list/${categoryId}"
    }

    object CategoryList : Screens("category_list")
    object Profile : Screens("profile")
    object Login : Screens("login")
    object Signup : Screens("signup")
}