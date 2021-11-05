package com.example.envelope.core.di

import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideFireBase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
}