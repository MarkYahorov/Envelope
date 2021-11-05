package com.example.envelope.presentation.contacts.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.envelope.databinding.FragmentContactsBinding
import com.example.envelope.presentation.contacts.ui.adapter.ContactsAdapter
import com.example.envelope.presentation.contacts.ui.viewModel.ContactsViewModel
import dagger.Lazy
import javax.inject.Inject


class ContactsFragment : Fragment() {

    @Inject
    lateinit var factory: Lazy<ContactsViewModel.Factory>
    private val contactsViewModel: ContactsViewModel by viewModels { factory.get() }
    private val args by navArgs<ContactsFragmentArgs>()
    private var _binding: FragmentContactsBinding? = null
    private val binding
        get() = _binding ?: error("NOT BINDING")
    private val contactsAdapter by lazy {
        ContactsAdapter {
            if (args.isNewChat) {
                contactsViewModel.createNewDialog(it.id)
            } else {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentContactsBinding.inflate(layoutInflater, container,false)
        return binding.root
    }
}