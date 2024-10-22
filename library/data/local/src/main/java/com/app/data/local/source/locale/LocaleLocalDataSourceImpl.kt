package com.app.data.local.source.locale

import com.app.data.locale.ApplicationLocaleManager
import com.app.domain.model.locale.LocaleKeyBO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

class LocaleLocalDataSourceImpl(
    private val localeManager: ApplicationLocaleManager, // not using full capability
) : LocaleLocalDataSource {

    private val _currentLocale = MutableStateFlow(localeManager.getLocale())
    override val currentLocale = _currentLocale.asStateFlow()

    override fun updateLocale(locale: Locale) {
        _currentLocale.value = locale
    }

    override fun getLocaleKeysList() = listOf(
        LocaleKeyBO.EN.key,
        LocaleKeyBO.AR.key,
    )
}
