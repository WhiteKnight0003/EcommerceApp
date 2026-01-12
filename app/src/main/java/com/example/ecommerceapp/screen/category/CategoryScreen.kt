package com.example.ecommerceapp.screen.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.screen.navigator.Screens

@Composable
fun CategoryScreen(
    navController: NavController
) {
    val categories: List<Category> = listOf(
        Category("1", "Elec", "https://cdn-icons-png.flaticon.com/128/18405/18405048.png"),
        Category("2", "Elecczxczxc", "https://cdn-icons-png.flaticon.com/128/17408/17408214.png")
    )

    Column(){
        if(categories.isEmpty()){
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "No Categories found !",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }else{
            Text(
                "Categories",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(
                    horizontal = 16.dp, vertical = 8.dp
                )
            )

            // categories grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                items(categories){ category ->
                    CategoryItem(
                        category,
                        onClick = {
                            navController.navigate(
                                Screens.ProductsList.createRoute(category.id)
                            )
                        }
                    )
                }
            }
        }
    }
}