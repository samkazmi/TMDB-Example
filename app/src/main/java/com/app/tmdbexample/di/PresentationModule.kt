package com.app.tmdbexample.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.app.tmdbexample.ui.search.vm.SearchViewModel
import com.app.tmdbexample.ui.details.vm.DetailViewModel
import com.app.tmdbexample.ui.player.vm.PlayerViewModel
import com.app.tmdbexample.ui.common.LocaleViewModel

val presentationModule = module {
    viewModelOf(::SearchViewModel)
    viewModelOf(::LocaleViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::PlayerViewModel)
}
