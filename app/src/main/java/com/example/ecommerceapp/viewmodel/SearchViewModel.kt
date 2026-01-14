package com.example.ecommerceapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.repositories.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: FirestoreRepository
) : ViewModel(){

    private val _searchResults = MutableStateFlow<List<Product>>(emptyList())
    val searchResults : MutableStateFlow<List<Product>> = _searchResults

    private val _isSearchching = MutableStateFlow(false)
    val isSearchching : MutableStateFlow<Boolean> get() = _isSearchching

    fun searchProducts(query: String){
        if(query.isBlank()){
            _searchResults.value = emptyList()
            _isSearchching.value = false
            return
        }
        _isSearchching.value = true
        viewModelScope.launch{
            _searchResults.value = repository.searchProducts(query.lowercase())
            _isSearchching.value = false
        }
    }
}