package com.example.envelope.presentation.contacts.di

import com.example.envelope.core.di.AppComponent
import com.example.envelope.presentation.contacts.ui.screen.ContactsFragment
import dagger.Component

@Component(dependencies = [AppComponent::class])
interface ContactsComponent {

    @Component.Factory
    interface Factory {
        fun create(appComponent: AppComponent): ContactsComponent
    }

    fun inject(contactsFragment: ContactsFragment)
}