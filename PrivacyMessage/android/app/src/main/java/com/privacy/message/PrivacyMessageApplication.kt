package com.privacy.message

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class PrivacyMessageApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        // Timber logging setup
        Timber.plant(Timber.DebugTree())
        
        Timber.d("PrivacyMessage Application started")
    }
}