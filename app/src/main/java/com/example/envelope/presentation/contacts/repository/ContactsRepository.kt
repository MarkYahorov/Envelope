package com.example.envelope.presentation.contacts

import com.example.envelope.presentation.chats.models.Chat
import com.example.envelope.presentation.contacts.models.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class ContactsRepository @Inject constructor(private val firebaseDatabase: FirebaseDatabase) {

    fun getContacts(callBack: ContactsCallBack) {
        val list = mutableListOf<Users>()
        object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.child("users").children.forEach {
                    val b = it.getValue(Users::class.java)
                    if (b!=null) {
                        list.add(b)
                    }
                }
                callBack.getData(list)
            }

            override fun onCancelled(error: DatabaseError) {
                callBack.getError(error)
            }

        }
    }

    fun createDialog(id: String) {
//        firebaseDatabase.reference.child("chats").child(id).setValue()
    }
}

interface ContactsCallBack {
    fun getData(list: List<Users>)
    fun getError(error: DatabaseError)
}