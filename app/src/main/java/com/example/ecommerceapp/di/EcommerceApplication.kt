package com.example.ecommerceapp.di

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EcommerceApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        // Init firebase
        FirebaseApp.initializeApp(this)
    }
}