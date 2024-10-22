package com.app.tmdbexample.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

fun <T> MutableSharedFlow<T>.pushEvent(event: T) {
    tryEmit(event)
}

fun <T> Flow<T>.observe(
    fragment: Fragment,
    action: suspend (T) -> Unit,
) = fragment.viewLifecycleOwner.lifecycleScope.launch {
    fragment.repeatOnLifecycle(Lifecycle.State.STARTED) {
        collect(action)
    }
}

fun <T> Flow<T>.observe(
    activity: AppCompatActivity,
    action: suspend (T) -> Unit,
) = activity.lifecycleScope.launch {
    activity.repeatOnLifecycle(Lifecycle.State.STARTED) {
        collect(action)
    }
}
