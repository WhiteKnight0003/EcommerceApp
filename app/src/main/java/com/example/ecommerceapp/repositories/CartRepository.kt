package com.example.ecommerceapp.repositories

import android.util.Log
import android.widget.Toast
import com.example.ecommerceapp.model.Product
import com.example.ecommerceapp.room.CartDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Acts as a bridge bet. ViewModel and ROOM DB
class CartRepository @Inject constructor(
    private val cartDao: CartDao
){
    // lấy tất cả sản phẩm trong giỏ hàng
    val allCartItems : Flow<List<Product>> = cartDao.getAllCartItems()

    suspend fun addToCart(product: Product){
        val existingItem = cartDao.getCartItemById(product.id)

        if(existingItem != null){
            Log.v("TagY",  "Product already Added!")
            cartDao.updateCartItem(product)
        }else{
            cartDao.insertCartItem(product)
            Log.v("TagY",  "Product Added!")
        }
    }

    suspend fun removeCartItem(product: Product){
        cartDao.deleteCartItem(product)
        Log.v("TagY",  "Product Removed!")
    }

    suspend fun clearCart(){
        cartDao.clearCart()
        Log.v("TagY",  "Clear Cart Success")
    }
}