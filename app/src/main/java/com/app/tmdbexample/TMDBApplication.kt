package com.app.tmdbexample

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.request.CachePolicy
import com.app.tmdbexample.di.ServiceLocator
import org.koin.core.component.KoinComponent

class TMDBApplication : Application(), KoinComponent, ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.initialize(
            application = this
        )
    }

    override fun newImageLoader() = ImageLoader.Builder(this)
        .crossfade(true)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .networkCachePolicy(CachePolicy.READ_ONLY)
        .error(R.color.gray)
        .placeholder(R.color.purple_200)
        .build()
}
