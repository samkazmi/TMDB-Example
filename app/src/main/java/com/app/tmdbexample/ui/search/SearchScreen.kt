package com.app.tmdbexample.ui.search

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.app.domain.model.multiSearch.MultiSearchBO
import com.app.tmdbexample.BuildConfig
import com.app.tmdbexample.R
import com.app.tmdbexample.ui.common.LoaderFullScreen
import com.app.tmdbexample.ui.common.PreviewContainer
import com.app.tmdbexample.ui.common.SearchView
import com.app.tmdbexample.ui.search.vm.SearchViewModel
import com.app.tmdbexample.ui.common.EmptyStateIcon
import com.app.tmdbexample.ui.common.EmptyStateView

@Composable
fun SearchScreen(
    vm: SearchViewModel,
    onclick: (media: MultiSearchBO) -> Unit,
) {
    val state by vm.state.collectAsState(SearchViewModel.State.Initial())
    SearchScreen(state, vm, onclick)
}

@Composable
fun SearchScreen(
    state: SearchViewModel.State, vm: SearchViewModel, onclick: (media: MultiSearchBO) -> Unit,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Searchbar(vm)
            SearchList(state, onclick)
        }
    }
}

@Composable
private fun Searchbar(vm: SearchViewModel) {
    SearchView(onQueryChange = {
        vm.onSearchQueryChanged(it)
    }, onBackClick = {
        vm.onSearchQueryChanged("")
    })
}

@Composable
private fun SearchList(state: SearchViewModel.State, onclick: (MultiSearchBO) -> Unit) {
    when (state) {
        is SearchViewModel.State.Loading -> LoaderFullScreen()
        is SearchViewModel.State.Data -> {
            val focusManager = LocalFocusManager.current
            LazyColumn(modifier = Modifier.fillMaxSize().pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
                detectDragGestures { _, _ ->
                    focusManager.clearFocus()
                }
            }) {
                state.listItems.forEach { (mediaType, mediaList) ->
                    item { CarouselHeading(mediaType.value) }
                    item { Carousel(mediaList = mediaList, onclick) }
                }
            }
        }
        is  SearchViewModel.State.Initial -> EmptyStateView(
            icon = EmptyStateIcon(),
            title = stringResource(R.string.search_tv_shows_movies_and_more),
            subtitle = "",
            verticalArrangement = Arrangement.Center
        )
        is SearchViewModel.State.Error -> EmptyStateView(
            icon = EmptyStateIcon(R.drawable.bg_empty),
            title = stringResource(R.string.something_went_wrong),
            subtitle = state.throwable.message ?: stringResource(R.string.unable_to_find_any_results),
            verticalArrangement = Arrangement.Center
        )
        is  SearchViewModel.State.Empty -> EmptyStateView(
            icon = EmptyStateIcon(R.drawable.bg_empty),
            title = stringResource(R.string.no_results_found),
            subtitle = stringResource(R.string.try_searching_with_different_keyword),
            verticalArrangement = Arrangement.Center
        )
    }
}

@Composable
private fun CarouselHeading(category: String) {
    Text(
        modifier = Modifier.padding(16.dp), text = category.uppercase(), style = TextStyle(
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
    )
}

@Composable
private fun Carousel(mediaList: List<MultiSearchBO>, onclick: (media: MultiSearchBO) -> Unit) {
    LazyRow(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(mediaList.size) { index ->
            CarousalItem(mediaList[index], onclick)
        }
    }
}

@Composable
private fun CarousalItem(
    item: MultiSearchBO,
    onclick: (media: MultiSearchBO) -> Unit,
) {
    AsyncImage(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .size(120.dp, 200.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable {
                onclick(item)
            },
        model = BuildConfig.IMAGE_BASE_URL + item.imagePath,
        contentDescription = "",
        contentScale = ContentScale.Crop,
    )
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    PreviewContainer {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                SearchView(onQueryChange = {}, onBackClick = {})

                LazyColumn {
                    item {
                        CarouselHeading("Movies")
                    }

                    val list = listOf(
                        MultiSearchBO(
                            1,
                            "Cars",
                            "/ksi3j.jpg",
                            "Description is not available",
                            MultiSearchBO.MediaTypeBO.MOVIE
                        ),
                        MultiSearchBO(
                            2,
                            "Cars",
                            "/ksi3j.jpg",
                            "Description is not available",
                            MultiSearchBO.MediaTypeBO.MOVIE
                        ),
                        MultiSearchBO(
                            3,
                            "Cars",
                            "/ksi3j.jpg",
                            "Description is not available",
                            MultiSearchBO.MediaTypeBO.MOVIE
                        ),
                    )
                    item {
                        Carousel(list) {}
                    }

                    item {
                        CarouselHeading("Tv")
                    }

                    val tv = listOf(
                        MultiSearchBO(
                            1,
                            "Cars",
                            "/ksi3j.jpg",
                            "Description is not available",
                            MultiSearchBO.MediaTypeBO.TV
                        ),
                        MultiSearchBO(
                            2,
                            "Cars",
                            "/ksi3j.jpg",
                            "Description is not available",
                            MultiSearchBO.MediaTypeBO.TV
                        ),
                        MultiSearchBO(
                            3,
                            "Cars",
                            "/ksi3j.jpg",
                            "Description is not available",
                            MultiSearchBO.MediaTypeBO.TV
                        ),
                    )
                    item {
                        Carousel(tv) {}
                    }
                }
            }
        }
    }
}