package com.gaeio.app.util

import android.content.Context
import com.gaeio.app.app.App

object PreferenceHelper {
    private const val PREF_NAME = "gaeio_prefs"
    private const val KEY_STREAM_URL = "stream_url"
    private const val KEY_AUDIO_ENABLED = "audio_enabled"

    private val prefs = App.getContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveStreamUrl(url: String) {
        prefs.edit().putString(KEY_STREAM_URL, url).apply()
    }

    fun getStreamUrl(): String {
        return prefs.getString(KEY_STREAM_URL, "") ?: ""
    }

    fun saveAudioEnabled(enabled: Boolean) {
        prefs.edit().putBoolean(KEY_AUDIO_ENABLED, enabled).apply()
    }

    fun getAudioEnabled(): Boolean {
        return prefs.getBoolean(KEY_AUDIO_ENABLED, true)
    }
}