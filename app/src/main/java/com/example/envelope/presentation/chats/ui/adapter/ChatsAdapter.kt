package com.example.envelope.presentation.chats.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.envelope.presentation.chats.models.Chat
import com.example.envelope.databinding.ChatsItemBinding

class ChatsAdapter(private val glide: RequestManager, private val onClick: (Chat) -> Unit) :
    ListAdapter<Chat, ChatsAdapter.ChatViewHolder>(ChatsDiffUtils()) {

    class ChatViewHolder(
        private val binding: ChatsItemBinding,
        private val glide: RequestManager,
        private val onClick: (Chat) -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(chat: Chat) {
            with(chat) {
                binding.chatsItemMessage.text = message
                binding.chatsItemTitle.text = title
                binding.chatsItemTime.text = title
                glide.load(image).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.chatsItemImage)
            }
        }

        fun unBind() {
            glide.clear(binding.chatsItemImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val itemBinding =
            ChatsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatViewHolder(itemBinding, glide, onClick)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onViewRecycled(holder: ChatViewHolder) {
        holder.unBind()

        super.onViewRecycled(holder)
    }

    class ChatsDiffUtils : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat) = oldItem == newItem
    }
}