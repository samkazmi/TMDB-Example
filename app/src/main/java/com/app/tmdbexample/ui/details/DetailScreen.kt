package com.app.tmdbexample.ui.details

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircleOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.app.domain.model.multiSearch.MultiSearchBO
import com.app.tmdbexample.BuildConfig
import com.app.tmdbexample.R
import com.app.tmdbexample.ui.details.vm.DetailViewModel
import com.app.tmdbexample.ui.details.vm.MovieDetailsState
import com.app.tmdbexample.ui.theme.TMDBExampleTheme
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    item: MultiSearchBO,
    vm: DetailViewModel = koinViewModel(),
    onPlayClick: () -> Unit = {},
) {
    val state by vm.state.collectAsState()
    vm.updateState(item)
    DetailsScreen(state, onPlayClick)
}

@Composable
fun DetailsScreen(
    state: MovieDetailsState,
    onPlayClick: () -> Unit,
) {
    var animationVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(100)
        animationVisible = true
    }

    Column(
        Modifier
            .fillMaxSize(1f)
    ) {
        Box(contentAlignment = Alignment.Center) {
            SubcomposeAsyncImage(
                model = BuildConfig.IMAGE_BASE_URL + state.multiSearchBO.imagePath,
                loading = { MovieItemPlaceholder() },
                error = { MovieItemPlaceholder() },
                contentDescription = stringResource(R.string.media_image),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(280.dp)
                    .fillMaxWidth(1f)
            )
            PlayButton {
                onPlayClick()
            }
        }

        AnimatedVisibility(
            visible = animationVisible,
            enter = fadeIn(),
        ) {
            Text(
                text = state.multiSearchBO.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
                    .fillMaxWidth(1f),
            )
        }

        AnimatedVisibility(
            visible = animationVisible,
        ) {
            Text(
                text = state.multiSearchBO.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(16.dp, 8.dp)
                    .fillMaxWidth(1f),
            )
        }
    }
}

@Composable
private fun MovieItemPlaceholder() {
    Box(Modifier.background(MaterialTheme.colorScheme.onBackground))
}

@Composable
fun PlayButton(onClick: () -> Unit) {
    Icon(
        imageVector = Icons.Outlined.PlayCircleOutline,
        tint = Color.White,
        contentDescription = stringResource(R.string.play_video),
        modifier = Modifier
            .size(64.dp) // Adjust size as needed
            .padding(6.dp)
            .background(
                color = Color.Black.copy(alpha = 0.5f),
                shape = MaterialTheme.shapes.small
            )
            .clickable(onClick = onClick)
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MovieDetailsScreenPreview() {
    PreviewContainer {
        DetailsScreen(
            MultiSearchBO(
                id = 1,
                name = "Avatar",
                imagePath = "https://i.stack.imgur.com/lDFzt.jpg",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore.",
                adult = false,
                mediaType = MultiSearchBO.MediaTypeBO.MOVIE
            ),
        )
    }
}

@Composable
fun PreviewContainer(
    content: @Composable () -> Unit,
) {
    TMDBExampleTheme { content() }
}
