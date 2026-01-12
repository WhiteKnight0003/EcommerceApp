package com.example.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.category.CategoryScreen
import com.example.ecommerceapp.screen.cart.CartScreen
import com.example.ecommerceapp.screen.home.HomeScreen
import com.example.ecommerceapp.screen.profile.ProfileScreen
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // navigation
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "Home"
            ) {
                composable("Home"){
                    HomeScreen(
                        navController = navController,
                        onProfileClick = {navController.navigate("Profile")},
                        onCartClick = {navController.navigate("Cart")}
                    )
                }
                composable("Cart"){
                    CartScreen(navController = navController)
                }
                composable("Profile"){
                    ProfileScreen(navController = navController, onSignOut = {})
                }
                composable("Categories"){
                    CategoryScreen(navController = navController)
                }
            }
        }
    }
}

