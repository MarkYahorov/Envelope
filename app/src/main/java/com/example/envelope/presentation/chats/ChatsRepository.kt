package com.example.envelope.presentation.chats

import com.example.envelope.presentation.chats.models.Chat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatsRepository(private val fireBase: FirebaseDatabase) {

    fun fetchChats(userId: String, chasts: ChatsCallbacks) = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            val list = mutableListOf<Chat>()
            snapshot.child("users").child(userId).child("chats").children.forEach {
                val b = it.getValue(Chat::class.java)
                if (b != null) {
                    list.add(b)
                }
            }
            chasts.getData(list)
        }

        override fun onCancelled(error: DatabaseError) {
            chasts.getError(error)
        }
    }

}

interface ChatsCallbacks {
    fun getData(data: List<Chat>)
    fun getError(error: DatabaseError)
}