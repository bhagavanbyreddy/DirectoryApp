package com.example.directoryapp.contacts.presentation.contact_list

import com.example.directoryapp.contacts.domain.model.Contact

data class ContactsState(
    val contactsList: List<Contact> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
