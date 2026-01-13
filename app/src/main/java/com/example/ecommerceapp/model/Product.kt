package com.example.ecommerceapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// @Entity(tableName = "cartItems")
// Nó đánh dấu class này là một Thực thể.
// sẽ tự động tạo một bảng trong cơ sở dữ liệu SQLite của điện thoại dựa trên cấu trúc này.
@Entity(tableName = "cartItems")
data class Product(
    @PrimaryKey val id: String ="", // khóa chính
    val name: String ="",
    val price: Double =0.0,
    val imageUrl: String = "",
    val description: String = "",
    val categoryId: String = "",
    val feature: Boolean = false
)
