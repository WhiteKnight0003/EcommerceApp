package com.example.ecommerceapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.repositories.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository
): ViewModel() {

    val cartItems = repository.allCartItems

    fun addToCart(product: Product) = viewModelScope.launch {
        repository.addToCart(product)
    }

    fun removeItemFromCart(cartItem : Product) = viewModelScope.launch {
        repository.removeCartItem(cartItem)
    }

    fun clearCart() = viewModelScope.launch {
        repository.clearCart()
    }

    fun calculateTotal(items: List<Product>) : Double{
        return items.sumOf { it.price }
    }
}