package com.example.envelope.presentation.contacts.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.envelope.databinding.ContactsItemBinding
import com.example.envelope.presentation.contacts.models.Users

class ContactsAdapter(private val onClick: (Users) -> Unit) :
    ListAdapter<Users, ContactsAdapter.ContactViewHolder>(ContactDiffCallBack()) {

    class ContactViewHolder(private val binding: ContactsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: Users) {
            binding.contactItemName.text = user.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding =
            ContactsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding).apply {
            itemView.setOnClickListener {
                onClick(currentList[adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class ContactDiffCallBack : DiffUtil.ItemCallback<Users>() {
        override fun areItemsTheSame(oldItem: Users, newItem: Users) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Users, newItem: Users) = oldItem == newItem

    }
}