package com.example.envelope.core.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Component

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }

    fun getFireBase(): FirebaseDatabase
}