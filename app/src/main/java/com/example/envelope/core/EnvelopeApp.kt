package com.example.envelope.core

import android.app.Application
import com.example.envelope.core.di.AppComponent
import com.example.envelope.core.di.DaggerAppComponent
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EnvelopeApp: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create()
    }
}