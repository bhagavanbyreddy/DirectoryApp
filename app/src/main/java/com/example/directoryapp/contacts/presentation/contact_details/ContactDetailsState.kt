package com.example.directoryapp.contacts.presentation.contact_details

import com.example.directoryapp.contacts.domain.model.Contact

data class ContactDetailsState(
    val contactDetails: Contact = Contact(),
    val isLoading: Boolean = false,
    val error: String = ""
)
