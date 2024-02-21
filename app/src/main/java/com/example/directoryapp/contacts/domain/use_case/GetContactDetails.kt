package com.example.directoryapp.contacts.domain.use_case

import com.example.directoryapp.contacts.domain.model.Contact

class GetContactDetails {

    operator fun invoke(id: String, contactsList: List<Contact>): Contact? {
        return try {
            contactsList.first { it.id == id }
        } catch (e: NoSuchElementException) {
            null
        }
    }
}