package com.example.ecommerceapp.screen.cart

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.screen.navigator.Screens
import com.example.ecommerceapp.viewmodel.CartViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlin.collections.listOf

@Composable
fun CartScreen(
    navController : NavController,
    cartViewModel: CartViewModel = hiltViewModel()
) {
    val cartItems = cartViewModel.cartItems.collectAsState(initial = emptyList()).value
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(
            text = "Your Cart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if(cartItems.isEmpty()){ // giỏ hàng trống
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
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Text(text = "Continue Shopping")
                }
            }
        }else{
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(cartItems){ item ->
                    CartItemCard(
                        item = item,
                        onRemoveItem = { cartViewModel.removeItemFromCart(item)}
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
                        text = "$${cartViewModel.calculateTotal(cartItems)} $",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        cartViewModel.clearCart()
                        // Handle checkout
                        Toast.makeText(context, "Payment Success", Toast.LENGTH_SHORT).show()

                        // 2. Quay về trang chủ và dọn dẹp Stack điều hướng
                        navController.navigate(Screens.Home.route)
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp)
                ) {
                    Text(text = "Proceed to Checkout")
                }
            }
        }
    }
}