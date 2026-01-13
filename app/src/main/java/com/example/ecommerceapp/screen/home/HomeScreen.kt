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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.screen.navigator.Screens
import com.example.ecommerceapp.viewmodel.CategoryViewModel
import com.example.ecommerceapp.viewmodel.ProductViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController : NavController,
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit,
    productViewModel: ProductViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel()
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
                navController.navigate(Screens.CategoryList.route)
            }
            // Mock the categories
            val selectedCategory = remember { mutableStateOf(0) }
            val categories = categoryViewModel.categories.collectAsState().value

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
            productViewModel.getAllProductsInFirestore() // call fun
            val products = productViewModel.allproduct.collectAsState().value

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products){ product ->
                    FeatureProducCart(product) {
                        navController.navigate(Screens.ProductDetails.createRoute(product.id))
                    }

                }
            }
        }
    }
}