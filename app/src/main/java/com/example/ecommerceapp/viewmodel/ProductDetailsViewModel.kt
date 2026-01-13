package com.example.ecommerceapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.repositories.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: FirestoreRepository
): ViewModel(){
    private val _product = MutableStateFlow<Product?>(null)
    val product : StateFlow<Product?> get() = _product

    private fun fetchProductDetails(productId: String){
        viewModelScope.launch {
            try{
                val products = repository.getProductById(productId)
                     _product.value = products
            }catch (e: Exception){
                Log.e("TagY", "Error fetching products details ${e.message}")
            }
        }
    }
}