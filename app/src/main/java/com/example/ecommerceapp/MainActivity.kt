package com.example.ecommerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.screen.category.CategoryScreen
import com.example.ecommerceapp.screen.cart.CartScreen
import com.example.ecommerceapp.screen.home.BottomNavigationBar
import com.example.ecommerceapp.screen.home.HomeScreen
import com.example.ecommerceapp.screen.home.MyTopAppBar
import com.example.ecommerceapp.screen.navigator.Screens
import com.example.ecommerceapp.screen.products.ProductDetailsScreen
import com.example.ecommerceapp.screen.products.ProductScreen
import com.example.ecommerceapp.screen.profile.LoginScreen
import com.example.ecommerceapp.screen.profile.ProfileScreen
import com.example.ecommerceapp.screen.profile.SignUpScreen
import com.example.ecommerceapp.viewmodel.AuthViewModel
import com.example.ecommerceapp.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // navigation
            val navController = rememberNavController()
            val  authViewModel: AuthViewModel = hiltViewModel()
            val cartViewModel: CartViewModel = hiltViewModel()

            val isLoggedIn = remember { derivedStateOf { authViewModel.isLoggedIn } }

            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry.value?.destination?.route

            val cartCount = cartViewModel.cartItems.collectAsState(initial = emptyList())
            val itemCount = cartCount.value.size

            val hideBar = listOf(
                Screens.Signup.route,
                Screens.Login.route
            )
            Scaffold(
                topBar = {
                    if (currentRoute !in hideBar) {
                        MyTopAppBar(
                            onProfileClick = { navController.navigate(Screens.Profile.route) },
                            onCartClick = { navController.navigate(Screens.Cart.route) })
                    }
                 },
                bottomBar = {
                    if(currentRoute !in hideBar){
                        BottomNavigationBar(
                            navController,
                            itemCount
                        )
                    }
                },
            )
            { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = Screens.Home.route,
                    modifier = Modifier.padding(paddingValues)
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
                        ProfileScreen(
                            navController = navController,
                            onSignOut = {
                                authViewModel.SignOut()
                                navController.navigate(Screens.Login.route){
                                    popUpTo(0) { inclusive = true }
                                }
                            }
                        )
                    }
                    // k đăng nhập vẫn cho dùng app
                    // muốn xem màn hình category thì cần login
                    composable(Screens.CategoryList.route){
                        CategoryScreen(
                            navController = navController,
                            onCartClick = { navController.navigate(Screens.Cart.route)},
                            onProfileClick = {
                                if(isLoggedIn.value){
                                    navController.navigate(Screens.Profile.route)
                                }else{
                                    navController.navigate(Screens.Login.route)
                                }
                            }
                        )
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
}

