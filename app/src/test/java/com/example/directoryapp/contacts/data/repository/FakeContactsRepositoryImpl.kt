package com.example.directoryapp.contacts.data.repository

import com.example.directoryapp.common.Resource
import com.example.directoryapp.contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeContactsRepositoryImpl: ContactsRepository {


    private val contactList = mutableListOf<Contact>()

    override fun getContactList(): Flow<Resource<List<Contact>>> {
        return flow { emit(Resource.Success(data = contactList)) }
    }

    fun insertContact(contact: Contact) {
        contactList.add(contact)
    }
}