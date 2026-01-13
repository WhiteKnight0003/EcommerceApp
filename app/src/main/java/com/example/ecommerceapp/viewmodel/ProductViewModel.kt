package com.example.ecommerceapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.repositories.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: FirestoreRepository
): ViewModel(){
    // Encapsulation : mutable internally , read-only externally
    private val _productByCategory = MutableStateFlow<List<Product>>(emptyList())
    val productByCategory : StateFlow<List<Product>> get() = _productByCategory

    private val _allproduct = MutableStateFlow<List<Product>>(emptyList())
    val allproduct : StateFlow<List<Product>> get() = _allproduct

    fun fetchProductsByCategory(categoryId: String){
        viewModelScope.launch {
            try{
                val products = repository.getProductByCategory(categoryId)
                _productByCategory.value = products
            }catch (e: Exception){
                Log.e("TagY", "Error fetching products ${e.message}")
            }
        }
    }

    fun getAllProductsInFirestore(){
        viewModelScope.launch {
            try{
                val products = repository.getAllProductInFireStore()
                    _allproduct.value = products
            }catch (e: Exception){
                Log.e("TagY", "Error get all products ${e.message}")
            }
        }
    }

}