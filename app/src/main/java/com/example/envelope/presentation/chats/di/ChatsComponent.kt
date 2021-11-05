package com.example.envelope.presentation.chats.di

import com.example.envelope.core.di.AppComponent
import com.example.envelope.core.di.ScreenScope
import com.example.envelope.presentation.chats.ui.screen.ChatsFragment
import dagger.Component

@Component(dependencies = [AppComponent::class], modules = [ChatsModule::class])
@ScreenScope
interface ChatsComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ChatsComponent
    }

    fun inject(chatsFragment: ChatsFragment)
}