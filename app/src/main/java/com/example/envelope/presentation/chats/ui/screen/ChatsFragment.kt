package com.example.envelope.presentation.chats.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.envelope.core.EnvelopeApp
import com.example.envelope.databinding.FragmentChatsBinding
import com.example.envelope.presentation.chats.di.DaggerChatsComponent
import com.example.envelope.presentation.chats.ui.adapter.ChatsAdapter
import com.example.envelope.presentation.chats.ui.viewModel.ChatsViewModel
import dagger.Lazy
import javax.inject.Inject

class ChatsFragment : Fragment() {

    @Inject
    internal lateinit var factory: Lazy<ChatsViewModel.Factory>
    private val chatsViewModel: ChatsViewModel by viewModels { factory.get() }
    private var _binding: FragmentChatsBinding? = null
    private val binding
        get() = _binding ?: error("NOT BINDING")
    private val chatsAdapter: ChatsAdapter by lazy {
        ChatsAdapter(Glide.with(this)) {
            val action = ChatsFragmentDirections.actionChatsFragmentToChatFragment(it.id)
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerChatsComponent.factory().create(EnvelopeApp.appComponent).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChatsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        chatsViewModel.liveData.observe(viewLifecycleOwner, {
            chatsAdapter.submitList(it)
        })
        chatsViewModel.get("1")
    }

    private fun initRecycler() {
        with(binding.chatsRecycler) {
            adapter = chatsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onStart() {
        super.onStart()

        setAddBtnListener()

    }

    private fun setAddBtnListener() {
        binding.chatsAddBtn.setOnClickListener {
            val action =
                ChatsFragmentDirections.actionChatsFragmentToContactsFragment(isNewChat = true)
            findNavController().navigate(action)
        }
    }

    override fun onStop() {
        super.onStop()

        binding.chatsAddBtn.setOnClickListener(null)
    }
}