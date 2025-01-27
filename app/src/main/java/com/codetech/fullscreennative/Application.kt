package com.codetech.fullscreennative

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Application:Application() {
    override fun onCreate() {
        super.onCreate()
        //disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // admob initialization
        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(this@Application)
        }

        //set text device for real admob ids
        setTestingDevices()
    }

    private fun setTestingDevices() {
        val testDeviceIds = listOf("YOUR DEVICE ID")
        val configuration = RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
        MobileAds.setRequestConfiguration(configuration)
    }
}