package com.thermondo.androidchallenge

import android.app.Application
import com.thermondo.androidchallenge.di.networkModule
import com.thermondo.androidchallenge.di.serviceModule
import com.thermondo.androidchallenge.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SpaceLaunchApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@SpaceLaunchApplication)
            modules(networkModule,serviceModule,viewModelModule)
        }
    }
}