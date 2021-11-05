package com.example.envelope.presentation.chats.di

import com.example.envelope.core.di.ScreenScope
import com.example.envelope.presentation.chats.ChatsRepository
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides

@Module
class ChatsModule {

    @Provides
    @ScreenScope
    fun provideRepository(firebaseDatabase: FirebaseDatabase): ChatsRepository {
        return ChatsRepository(firebaseDatabase)
    }
}