package com.app.tmdbexample.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.ActivityNavigatorDestinationBuilder
import androidx.navigation.NavHostController
import androidx.navigation.activity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.app.tmdbexample.ui.details.DetailsScreen
import com.app.tmdbexample.ui.player.PlayerActivity
import com.app.tmdbexample.ui.search.SearchScreen
import com.app.tmdbexample.ui.search.vm.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainGraph(
    padding: PaddingValues,
    navController: NavHostController,
) {
    NavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = SearchRoute
    ) {
        composable<SearchRoute> {
            val vm: SearchViewModel = koinViewModel()
            SearchScreen(vm = vm) { item ->
                navController.navigate(item.toMediaDetailRoute())
            }
        }
        composable<DetailRoute> { backStackEntry ->
            val profile: DetailRoute = backStackEntry.toRoute()
            DetailsScreen(profile.toMultiSearchBO()) {
                navController.navigate(PlayerRoute)
            }
        }
        activity<PlayerRoute> {
            activityClass = PlayerActivity::class

        }
    }
}