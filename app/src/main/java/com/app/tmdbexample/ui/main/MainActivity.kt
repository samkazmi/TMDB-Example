package com.app.tmdbexample.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.tmdbexample.ui.common.AppTopBar
import com.app.tmdbexample.ui.common.LocaleViewModel
import com.app.tmdbexample.ui.common.LocalizeApp
import com.app.tmdbexample.ui.navigation.DetailRoute
import com.app.tmdbexample.ui.navigation.MainGraph
import com.app.tmdbexample.ui.theme.TMDBExampleTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDBExampleTheme {
                var language by remember { mutableStateOf("") }
                LocalizeApp(language) {
                    App(onLanguageChange = {
                        language = it
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    TMDBExampleTheme {
        var language by remember { mutableStateOf("") }
        LocalizeApp(language) {
            App(onLanguageChange = {
                language = it
            })
        }
    }
}

@Composable
private fun App(onLanguageChange: (lang: String) -> Unit) {
    val navController = rememberNavController()
    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route
    val vm: LocaleViewModel = koinViewModel()
    Scaffold(
        topBar = {
            AppTopBar(
                viewmodel = vm,
                showBackButton = currentDestination?.contains(DetailRoute::class.java.name) ?: false,
                onLanguageChange = onLanguageChange,
                navController = navController
            )
        }
    ) { padding ->
        MainGraph(padding, navController)
    }
}