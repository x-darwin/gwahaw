package com.gaeio.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gaeio.app.util.PreferenceHelper

class MainViewModel : ViewModel() {
    
    private val _streamUrl = MutableLiveData<String>()
    val streamUrl: LiveData<String> = _streamUrl

    private val _audioEnabled = MutableLiveData<Boolean>()
    val audioEnabled: LiveData<Boolean> = _audioEnabled

    private val _saveSuccess = MutableLiveData<Boolean>()
    val saveSuccess: LiveData<Boolean> = _saveSuccess

    fun loadSettings() {
        _streamUrl.value = PreferenceHelper.getStreamUrl()
        _audioEnabled.value = PreferenceHelper.getAudioEnabled()
    }

    fun saveStreamUrl(url: String, audioEnabled: Boolean) {
        PreferenceHelper.saveStreamUrl(url)
        PreferenceHelper.saveAudioEnabled(audioEnabled)
        _saveSuccess.value = true
    }
}