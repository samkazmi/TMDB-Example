package com.app.data.locale

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale
/**
 * This is a demo of handling app locales and changes
 * */
class ApplicationLocaleManager(private val application: Application) {

    fun getLocale(): Locale {
        val locales = application.resources.configuration.locales
        for (i in 0 until locales.size()) {
            val locale = locales.get(i)
            val language = locale.language
            if (language == "en" || language == "ar") {
                return locale
            }
        }
        return Locale("en", "US")
    }

    fun setLocale(locale: Locale) {
        val configuration = application.resources.configuration
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale.toString()))
        AppCompatDelegate.getApplicationLocales()[0]?.let { updatedLocale ->
            configuration.setLocale(updatedLocale)
        }
    }
}
