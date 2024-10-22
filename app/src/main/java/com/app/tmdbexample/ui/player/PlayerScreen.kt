package com.app.tmdbexample.ui.player

import android.app.Activity
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.app.tmdbexample.ui.player.vm.PlayerViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Media3PlayerView(videoUrl: String, playerViewModel: PlayerViewModel = koinViewModel()) {

    val context = LocalContext.current
    val player by playerViewModel.playerState.collectAsState()

    LaunchedEffect(videoUrl) {
        playerViewModel.initializePlayer(context, videoUrl)
    }

    DisposableEffect(Unit) {
        onDispose {
            playerViewModel.savePlayerState()
            playerViewModel.releasePlayer()
        }
    }
    Column {
        Media3AndroidView(player)
    }

}

@Composable
fun Media3AndroidView(player: ExoPlayer?) {
    val window = (LocalContext.current as? Activity)?.window
    val windowInsetsController = remember {
        window?.let { WindowCompat.getInsetsController(it, it.decorView) }
    }
    // Function to toggle system bars visibility
    fun toggleSystemBars(visibility: Int) {
        if (visibility == View.VISIBLE) {
            windowInsetsController?.show(WindowInsetsCompat.Type.systemBars())
        } else {
            windowInsetsController?.hide(WindowInsetsCompat.Type.systemBars())
        }
    }

    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        factory = { context ->
            PlayerView(context).apply {
                useController = true
                keepScreenOn = true
                this.player = player
                this.setControllerVisibilityListener(PlayerView.ControllerVisibilityListener {
                    toggleSystemBars(it)
                })
            }
        },
        update = { playerView ->
            playerView.player = player
        }
    )
}