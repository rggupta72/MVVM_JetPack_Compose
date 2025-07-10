package com.example.composepoc

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) { // Only plant in debug builds
            Timber.plant(Timber.DebugTree())
        } else {
            // In release builds, you might plant a tree that reports to a crash reporting service
            // Timber.plant(CrashReportingTree())
        }
    }
}