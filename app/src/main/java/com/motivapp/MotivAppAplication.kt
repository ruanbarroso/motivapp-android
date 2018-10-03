package com.motivapp

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

class MotivAppAplication : MultiDexApplication() {

    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}