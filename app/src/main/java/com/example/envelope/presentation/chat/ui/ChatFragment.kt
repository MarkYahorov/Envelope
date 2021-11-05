package com.example.envelope.presentation.chat.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.envelope.databinding.FragmentChatBinding
import com.example.envelope.presentation.chat.ui.adapter.ChatAdapter


class ChatFragment : Fragment() {

    private val args by navArgs<ChatFragmentArgs>()
    private var _binding: FragmentChatBinding? = null
    private val binding
        get() = _binding ?: error("NOT BINDING")
    private val chatAdapter by lazy {
        ChatAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initChatRecycler()
    }

    private fun initChatRecycler() {
        with(binding.chatRecycler) {
            adapter = chatAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onStart() {
        super.onStart()

        setSendBtnListener()
    }

    private fun setSendBtnListener() {
        binding.chatSendBtn.setOnClickListener {

        }
    }

    override fun onStop() {
        super.onStop()

        binding.chatSendBtn.setOnClickListener(null)
    }
}