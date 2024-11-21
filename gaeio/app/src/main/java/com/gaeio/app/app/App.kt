package com.gaeio.app.app

import android.app.Application
import android.content.Context

class App : Application() {
    companion object {
        private lateinit var instance: App
        
        fun getContext(): Context = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}