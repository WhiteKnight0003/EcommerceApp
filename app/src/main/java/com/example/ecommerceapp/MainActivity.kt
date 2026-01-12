package com.example.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.screen.category.CategoryScreen
import com.example.ecommerceapp.screen.cart.CartScreen
import com.example.ecommerceapp.screen.home.HomeScreen
import com.example.ecommerceapp.screen.navigator.Screens
import com.example.ecommerceapp.screen.products.ProductScreen
import com.example.ecommerceapp.screen.profile.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // navigation
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home"){
                    HomeScreen(
                        navController = navController,
                        onProfileClick = {navController.navigate(Screens.Profile.route)},
                        onCartClick = {navController.navigate(Screens.Cart.route)}
                    )
                }
                composable("cart"){
                    CartScreen(navController = navController)
                }
                composable("profile"){
                    ProfileScreen(navController = navController, onSignOut = {})
                }
                composable("categories"){
                    CategoryScreen(navController = navController)
                }
//                composable("products"){
//                    ProductScreen(navController = navController)
//                }
            }
        }
    }
}

