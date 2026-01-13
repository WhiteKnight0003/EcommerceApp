package com.example.ecommerceapp.screen.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.screen.navigator.Screens
import com.example.ecommerceapp.viewmodel.ProductViewModel

@Composable
fun ProductScreen(
    categoryId: String,
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel(),
) {

    // Fetch products from the viewmodel


    // product data
    LaunchedEffect(categoryId) {
        productViewModel.fetchProductsByCategory(categoryId)// call fun
    }
    val products = productViewModel.productByCategory.collectAsState().value

    // Display the products
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Products of Category ID : $categoryId",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        // if there is no products
        if(products.isEmpty()){
            Text(
                text ="No products Found!",
                modifier = Modifier.padding(16.dp)
            )
        }else{
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(products){ product ->
                    ProductItem(
                        product = product,
                        onClick = {
                            navController.navigate(Screens.ProductDetails.createRoute(product.id))
                        }, // navigate Product details
                        onAddToCart = {}, // navigate cart
                    )
                }
            }
        }
    }
}