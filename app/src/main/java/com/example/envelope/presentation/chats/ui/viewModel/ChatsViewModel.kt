package com.example.envelope.presentation.chats.ui.viewModel

import androidx.lifecycle.*
import com.example.envelope.presentation.chats.ChatsCallbacks
import com.example.envelope.presentation.chats.ChatsRepository
import com.example.envelope.presentation.chats.models.Chat
import com.google.firebase.database.DatabaseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class ChatsViewModel(private val repository: ChatsRepository) : ViewModel(), ChatsCallbacks {

    private val _liveData = MutableLiveData<List<Chat>>()
    val liveData: LiveData<List<Chat>> = _liveData

    fun get(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchChats(userId, this@ChatsViewModel)
        }
    }

    override fun getData(data: List<Chat>) {
        _liveData.value = data
    }

    override fun getError(error: DatabaseError) {
        TODO("Not yet implemented")
    }

    class Factory @Inject constructor(private val repository: Provider<ChatsRepository>) :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == ChatsViewModel::class.java)
            return ChatsViewModel(repository.get()) as T
        }

    }
}