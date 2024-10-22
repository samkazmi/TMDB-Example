package com.app.tmdbexample.ui.common

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.tmdbexample.R

@Composable
fun EmptyStateView(
    modifier: Modifier = Modifier,
    icon: EmptyStateIcon = EmptyStateIcon(),
    title: String? = null,
    subtitle: String? = null,
    titleTextSize: TextUnit = 18.sp,
    subtitleTextSize: TextUnit = 14.sp,
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
                detectDragGestures { _, _ ->
                    focusManager.clearFocus()
                }
            },
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        icon.iconRes?.let {
            Image(
                modifier = Modifier.size(icon.size),
                painter = painterResource(id = it),
                contentScale = ContentScale.Fit,
                contentDescription = stringResource(R.string.empty_state_icon)
            )
        }
        Spacer(modifier = Modifier.padding(icon.spacing))
        title?.let {
            Text(
                text = it,
                textAlign = TextAlign.Center,
                fontSize = titleTextSize,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.padding(8.dp))

        subtitle?.let {
            Subtitle(it, subtitleTextSize)
        }

    }
}

data class EmptyStateIcon(
    @DrawableRes val iconRes: Int? = null,
    val size: Dp = 200.dp,
    val spacing: Dp = 10.dp,
)

@Composable
private fun Subtitle(text: String, subtitleTextSize: TextUnit) {
    Text(
        text = text,
        fontSize = subtitleTextSize,
        lineHeight = 24.sp,
        textAlign = TextAlign.Center,
    )
}

@Preview("Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun EmptyStateViewPreview() {
    PreviewContainer {
        Surface {
            EmptyStateView(
                icon = EmptyStateIcon(R.drawable.bg_empty),
                title = stringResource(R.string.no_results_found),
                subtitle = stringResource(R.string.try_searching_with_different_keyword),
            )
        }
    }
}