package com.example.ecommerceapp.screen.home

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {MyTopAppBar()},
        bottomBar = {BottomNavigationBar()},
    )
    {
        Text(text = "Demo")
    }
}