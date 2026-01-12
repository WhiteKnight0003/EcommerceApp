package com.example.ecommerceapp.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.screen.navigator.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController : NavController,
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit
){
    Scaffold(
        topBar = {MyTopAppBar(onProfileClick, onCartClick)},
        bottomBar = {BottomNavigationBar()},
    )
    { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        )
        {
            // Search section
            var searchQuery = remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current
            SearchBar(
                query = searchQuery.value,
                onQueryChange = {searchQuery.value = it},
                onSearch = {},
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            // Search result section

            // category section
            SectionTile("Category", "See All") {
                // navController.navigate(Screens.CategoryList.route)
            }
            // Mock the categories
            val selectedCategory = remember { mutableStateOf(0) }
            val categories: List<Category> = listOf(
                Category(1, "Elec", "https://cdn-icons-png.flaticon.com/128/18405/18405048.png"),
                Category(2, "Elecczxczxc", "https://cdn-icons-png.flaticon.com/128/17408/17408214.png")
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(categories.size){
                    CategoryChip(
                        icon = categories[it].iconUrl,
                        text = categories[it].name,
                        isSelected = selectedCategory.value == it,
                        onClick = {
                            selectedCategory.value = it
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            // feature product section
            SectionTile("Feature Product", "See All") {
               // navController.navigate(Screens.Products.route)
            }
            // Mock the product
            // val selectedCategory = remember { mutableStateOf(0) }
            val products = listOf(
                Product("1", "TIvi", 100.5, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/i/tivi-xiaomi-qled-4k-a-pro-43-inch-2026_1_.png", 1),
                Product("4", "Quạt ", 50.6, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/q/u/qu_t_2.png", 2),
                Product("5", "Nồi chiên không dầu", 110.0, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/n/o/noi-chien-khong-dau-gaabor-af-45t01a-5l.1.png", 1),
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products){ product ->
                    FeatureProducCart(product) {

                    }

                }
            }
        }
    }
}