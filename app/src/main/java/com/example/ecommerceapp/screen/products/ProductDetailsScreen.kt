package com.example.ecommerceapp.screen.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerceapp.model.Product

@Composable
fun ProductDetailsScreen(
    productID: String,
    navController: NavController
) {
    // Ferch produuct Detaila

    val product = Product("1", "TIvi", 100.5, "https://cdn2.cellphones.com.vn/insecure/rs:fill:358:358/q:90/plain/https://cellphones.com.vn/media/catalog/product/t/i/tivi-xiaomi-qled-4k-a-pro-43-inch-2026_1_.png", "1")

    if(product == null){
        Text(text = "Produuct not found")
    }else{
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = product.imageUrl
                ),
                contentScale = ContentScale.Crop,
                contentDescription = "Prodct image",
                modifier = Modifier.fillMaxWidth().height(300.dp)
                                .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Produuct name:
            Text(
                text = product.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Produuct prire:
            Text(
                text = "$${ product.price}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Produuct description:
            Text(
                text = "Product Description",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }

        IconButton(
            onClick = {}, // add to card event
            modifier = Modifier.height(16.dp).background(
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Add to cart",
                tint = Color.White
            )
        }
    }

}