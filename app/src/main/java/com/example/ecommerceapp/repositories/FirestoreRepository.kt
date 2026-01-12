package com.example.ecommerceapp.repositories

import android.util.Log
import com.example.ecommerceapp.model.Category
import com.example.ecommerceapp.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // chỉ nên tồn tại 1 instance duy nhất
class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
){
    fun getCategoriesFlow(): Flow<List<Category>> =
        callbackFlow {
            val listenerRegistration = firestore.collection("Category")
                                            .addSnapshotListener { snapshot, error ->
                                                if(error != null){
                                                    println("Error fetching categories: ${error.message}")
                                                    return@addSnapshotListener
                                                }

                                                if(snapshot != null){
                                                    val categories = snapshot.toObjects(Category::class.java)
                                                    trySend(categories)
                                                }
                                             }

            // close the flow when the listener is no longer needed
            awaitClose {
                listenerRegistration.remove()
            }
        }

    suspend fun getProductByCategory(categoryId: String): List<Product>{
        return try {
            val result = firestore.collection("Product")
                            .whereEqualTo("categoryId", categoryId) // so sánh dữ liệu trong firebase và dữ liệu ta truyền vào
                            .get()
                            .await()

            result.toObjects(Product::class.java).also {
                Log.v("TagY", "Mapped Product: ${it}")
            }
        }catch (e: Exception){
            emptyList()
        }
    }

    suspend fun getProductById(productId: String): Product?{
        return try {
            val result = firestore.collection("Product")
                .document(productId)
                .get()
                .await()

            result.toObject(Product::class.java)
        }catch (e: Exception){
            null
        }
    }

    suspend fun getAllProductInFireStore(): List<Product>{
        return try {
            val result = firestore.collection("Product").get().await()
                            .documents
                            .mapNotNull { it.toObject(Product::class.java) }

            result
        }catch (e: Exception){
            emptyList()
        }
    }
}