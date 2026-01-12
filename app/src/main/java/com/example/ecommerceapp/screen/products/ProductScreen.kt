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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.screen.navigator.Screens

@Composable
fun ProductScreen(
    categoryId: String,
    navController: NavController
) {

    // Fetch products from the viewmodel


    // product data
    val products = listOf(
        Product("1", "TIvi", 100.5, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/i/tivi-xiaomi-qled-4k-a-pro-43-inch-2026_1_.png", "1"),
        Product("2", "Tu lanh", 200.5, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/m/a/may-rua-bat-doc-lap-bosch-sms6zci37q_1_.png", "2"),
        Product("3", "Điện thoại", 10.5, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/i/p/iphone-17-pro-max_3.jpg", "1"),
        Product("4", "Quạt ", 50.6, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/q/u/qu_t_2.png", "2"),
        Product("5", "Nồi chiên không dầu", 110.0, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/n/o/noi-chien-khong-dau-gaabor-af-45t01a-5l.1.png", "1"),
    )
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