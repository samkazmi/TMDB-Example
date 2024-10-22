package com.app.data.domain.repository

import com.app.domain.repository.LocaleRepository
import com.app.data.local.source.locale.LocaleLocalDataSource
import java.util.Locale

internal class LocaleRepositoryImpl(
    private val localeLocalDataSource: LocaleLocalDataSource
) : LocaleRepository {

    override val currentLocale = localeLocalDataSource.currentLocale

    override fun setLocale(locale: Locale) {
        localeLocalDataSource.updateLocale(locale)
    }

    override fun getLocaleKeysList() = localeLocalDataSource.getLocaleKeysList()
}
