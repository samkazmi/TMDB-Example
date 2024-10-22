package com.app.tmdbexample.ui.common

import androidx.lifecycle.ViewModel
import com.app.domain.repository.LocaleRepository
import java.util.Locale

class LocaleViewModel(
    private val localeRepository: LocaleRepository,
) : ViewModel() {
    val locale = localeRepository.currentLocale

    fun updateLocale(locale: String) {
        localeRepository.setLocale(Locale(locale))
    }

    fun getLocales() = localeRepository.getLocaleKeysList()
}