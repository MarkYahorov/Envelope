package com.example.envelope.presentation.contacts.ui.viewModel

import androidx.lifecycle.*
import com.example.envelope.presentation.contacts.ContactsCallBack
import com.example.envelope.presentation.contacts.ContactsRepository
import com.example.envelope.presentation.contacts.models.Users
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class ContactsViewModel(private val repository: ContactsRepository) : ViewModel(),
    ContactsCallBack {

    private val _contactsLiveData = MutableLiveData<List<Users>>()
    val contactsLiveData: LiveData<List<Users>> = _contactsLiveData

    fun fetchContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getContacts(this@ContactsViewModel)
        }
    }

    fun createNewDialog(id: String) {
        viewModelScope.launch (Dispatchers.IO){
            repository.createDialog(id: String)
        }
    }

    class Factory @Inject constructor(private val repository: Provider<ContactsRepository>) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ContactsViewModel::class.java)
            return ContactsViewModel(repository.get()) as T
        }
    }

    override fun getData(list: List<Users>) {
        _contactsLiveData.value = list
    }

    override fun getError(error: DatabaseError) {
        TODO("Not yet implemented")
    }
}