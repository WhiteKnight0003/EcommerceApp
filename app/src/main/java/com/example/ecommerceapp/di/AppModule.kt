package com.example.ecommerceapp.di

import android.content.Context
import com.example.ecommerceapp.repositories.CartRepository
import com.example.ecommerceapp.room.AppDatabase
import com.example.ecommerceapp.room.CartDao
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext applicationContext: Context) : AppDatabase{
        return AppDatabase.getDatabase(applicationContext)
    }

    @Provides
    fun provideCartDao(appDatabase: AppDatabase): CartDao{
        return appDatabase.cartDao()
    }

    @Provides
    fun provideCartRepository(cartDao: CartDao): CartRepository{
        return CartRepository(cartDao)
    }
}