package com.example.envelope.presentation.chat.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.envelope.databinding.AnotherMessageItemBinding
import com.example.envelope.databinding.MyMessageItemBinding
import com.example.envelope.presentation.chat.models.Message

class ChatAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(ChatDiffCallBack()) {

    companion object {
        private const val MY_MESSAGE = 0
        private const val ANOTHER_MESSAGE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MY_MESSAGE) {
            val myMessageBinding = MyMessageItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
            MyMessageHolder(myMessageBinding)
        } else {
            val anotherMessageBinding = AnotherMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AnotherMessageHolder(anotherMessageBinding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].isMyMessage) {
            MY_MESSAGE
        } else {
            ANOTHER_MESSAGE
        }
    }

    class MyMessageHolder(private val binding: MyMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.myMessageText.text = message.text
        }
    }

    class AnotherMessageHolder(private val binding: AnotherMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(message: Message) {
                binding.anotherMessageText.text = message.text
            }
    }

    class ChatDiffCallBack : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Message, newItem: Message) =
            oldItem == newItem
    }
}