package com.app.data.local.source.locale

import java.util.Locale
import kotlinx.coroutines.flow.StateFlow

interface LocaleLocalDataSource {

    val currentLocale: StateFlow<Locale>

    fun getLocaleKeysList(): List<String>

    fun updateLocale(locale: Locale)
}
