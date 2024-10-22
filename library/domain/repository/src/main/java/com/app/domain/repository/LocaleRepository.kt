package com.app.domain.repository

import java.util.Locale
import kotlinx.coroutines.flow.StateFlow

interface LocaleRepository {

    val currentLocale: StateFlow<Locale>

    fun setLocale(locale: Locale)

    fun getLocaleKeysList(): List<String>
}
