package com.example.ecommerceapp.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ecommerceapp.model.Product
import kotlin.collections.listOf

@Composable
fun CartScreen(navController : NavController) {
    var carItems = listOf(
        Product("1", "TIvi", 100.5, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/i/tivi-xiaomi-qled-4k-a-pro-43-inch-2026_1_.png", 1),
        Product("4", "Quạt ", 50.6, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/q/u/qu_t_2.png", 2),
        Product("5", "Nồi chiên không dầu", 110.0, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/n/o/noi-chien-khong-dau-gaabor-af-45t01a-5l.1.png", 1),
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Your Cart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if(carItems.isEmpty()){ // giỏ hàng trống
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Your Cart is Empty",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {}
                ) {
                    Text(text = "Continue Shopping")
                }
            }
        }else{
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(carItems){ item ->
                    CartItemCard(
                        item = item,
                        onRemoveItem = {}
                    )
                }
            }

            // total and checkout
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total: ",
                        style = MaterialTheme.typography.titleMedium
                    )
                    // calculator total price
                    Text(
                        text = "... $",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text(text = "Proceed to Checkout")
                }
            }
        }
    }
}