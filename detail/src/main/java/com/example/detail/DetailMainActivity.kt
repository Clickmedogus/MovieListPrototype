package com.example.detail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.compose.rememberNavController
import com.example.detail.ui.DetailCompose
import com.example.detail.ui.theme.MovieListPrototypeTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class DetailMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieListPrototypeTheme {
                // A surface container using the 'background' color from the theme
                DetailCompose(peopleIndex = 1, navController = rememberNavController())
            }
        }
    }
}
