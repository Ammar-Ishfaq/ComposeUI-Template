package com.m.ammar.composeTemplate

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
    }

    private fun initTimber() {
        //make sure you are using com.packagename.BuildConfig e.g: "com.ammar.abc.BuildConfig"
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Log.e("TIMBER", "Timber Start")
        } else Log.e("TIMBER", "Timber Stop")

    }

}
