package com.viesure.assigment.models

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}