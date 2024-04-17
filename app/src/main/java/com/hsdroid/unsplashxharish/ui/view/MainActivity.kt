package com.hsdroid.unsplashxharish.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.hsdroid.unsplashxharish.ui.theme.UnsplashxHarishTheme
import com.hsdroid.unsplashxharish.ui.view.home.HomeScreen
import com.hsdroid.unsplashxharish.ui.viewModel.MainViewModel
import com.hsdroid.unsplashxharish.utils.ImageCache
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnsplashxHarishTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(mainViewModel)
                }
            }
        }
    }
}