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
import com.example.ecommerceapp.screen.products.ProductDetailsScreen
import com.example.ecommerceapp.screen.products.ProductScreen
import com.example.ecommerceapp.screen.profile.LoginScreen
import com.example.ecommerceapp.screen.profile.ProfileScreen
import com.example.ecommerceapp.screen.profile.SignUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // navigation
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screens.Home.route //Screens.Signup.route
            ) {
                composable(Screens.Home.route){
                    HomeScreen(
                        navController = navController,
                        onProfileClick = {navController.navigate(Screens.Profile.route)},
                        onCartClick = {navController.navigate(Screens.Cart.route)}
                    )
                }
                composable(Screens.Cart.route){
                    CartScreen(navController = navController)
                }
                composable(Screens.Profile.route){
                    ProfileScreen(navController = navController, onSignOut = {})
                }
                composable(Screens.CategoryList.route){
                    CategoryScreen(navController = navController)
                }
                composable(Screens.Login.route){
                    LoginScreen(
                        navController = navController,
                        onLoginSucess = {
                            navController.navigate(Screens.Home.route)
                        },
                        onNavigateToSignUp = {
                            navController.navigate(Screens.Signup.route)
                        }
                    )
                }
                composable(Screens.Signup.route){
                    SignUpScreen(
                        navController = navController,
                        onSignUpSucess = {
                            navController.navigate(Screens.Home.route)
                        },
                        onNavigateToLogin = {
                            navController.navigate(Screens.Login.route)
                        }
                    )
                }

                composable(Screens.ProductDetails.route){
                    val productID = it.arguments?.getString("productId")
                    if(productID != null)
                        ProductDetailsScreen(productID = productID, navController = navController)
                }

                composable(Screens.ProductsList.route){
                    val categoryID = it.arguments?.getString("categoryId")
                    if(categoryID != null)
                        ProductScreen(categoryId = categoryID, navController = navController)
                }
            }
        }
    }
}

