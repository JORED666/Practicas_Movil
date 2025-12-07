package com.project.examenprctico

import android.app.Application

class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
            private set

        val applicationContext get() = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
